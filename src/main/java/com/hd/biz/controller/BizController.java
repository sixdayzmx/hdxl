package com.hd.biz.controller;

import cn.hutool.core.codec.Base64;
import cn.kingeid.guomi.SM2Utils;
import com.alibaba.fastjson.JSONObject;
import com.hd.biz.pojo.*;
import com.hd.biz.service.LoginCallBackService;
import com.hd.biz.util.HttpRequestTool;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

@Controller
@RequestMapping("/api")
public class BizController {

    @Autowired
    private LoginCallBackService loginCallBackService;

    /**
     * 根据其中的bizCode和bizType查询callBackAddr
     * @param bizReq
     * @return
     */
    @PostMapping("/login/callBack")
    public void callBack(@RequestBody BizReq bizReq, HttpServletResponse response) throws Exception {

        String bizCode = bizReq.getBizCode();
        String bizType = bizReq.getBizType();
        String addrType=bizReq.getAddrType();
        String addr = loginCallBackService.callBack(bizCode,bizType,addrType);

        JSONObject json = (JSONObject) JSONObject.toJSON(bizReq);
        System.out.println(json);

        System.out.println(addr);
        JSONObject jsonObject = HttpRequestTool.doRequestJson(addr, "POST", json.toJSONString());
        response.getWriter().write(jsonObject.toJSONString());
        System.out.println(jsonObject.get("code"));
    }

    /**
     * 根据bizCode和bizType查询地址然后将id传给这个地址获取到响应回来的数据做hash然后将其响应给调用我们的服务端
     * @param bizReqId
     * @param response
     * @throws IOException
     */
    @PostMapping("/sign/query")
    public void query(@RequestBody BizReqId bizReqId, HttpServletResponse response) throws IOException {
        String mes="null";
        System.out.println(bizReqId);
        String addr = loginCallBackService.callBack(bizReqId.getBizCode(), bizReqId.getBizType(),bizReqId.getAddrType());
//        String addr="http://10.142.12.94:8070/aio/businesssystem/source";
        CloseableHttpClient client = null;

        HttpGet httpGet = new HttpGet(addr+"/"+bizReqId.getId());
        client = HttpClients.createDefault();
        CloseableHttpResponse response1 = client.execute(httpGet);
        try {
            StatusLine statusLine = response1.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            //200为请求成功（只是请求成功，如进行查询，连上了，查询失败也是200，具体成功与否看返回的数据）
            if (statusCode == 200) {
                //获取返回值
                HttpEntity entity = response1.getEntity();
                mes = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response1.close();
        }
        System.out.println("这是返回的数据"+mes);

        String encode = Base64.encode(mes.getBytes("UTF-8"));
        JSONObject jsonobject = new JSONObject();

        String userId="31323334353637383132333435363738";//默认的
        String personPubKey ="0F10D241E1EF7FC9402CA07C64906D135F5DFC1C884B6DCB3599FE434B3AC66B6C7BB8D9890ACF31F455520644172123C92A15D6855EFF64234BDC9101E0C6FF";
        String[] pubKeyArr = {personPubKey.substring(0,personPubKey.length()/2),personPubKey.substring(personPubKey.length()/2)};
        BigInteger pubKeyx = new BigInteger(pubKeyArr[0],16);
        BigInteger pubKeyy = new BigInteger(pubKeyArr[1],16);
        BigInteger c=new BigInteger(userId);
        String zaMH = SM2Utils.getZaMH(userId.getBytes(), mes.getBytes(), pubKeyx, pubKeyy);
        jsonobject.put("signHash",zaMH);
        jsonobject.put("sourceData",encode);
        String dataSource = jsonobject.toJSONString();
        response.getWriter().write(dataSource);
        System.out.println(dataSource);
    }

    @PostMapping("/sign/insert")
    public void insert(@RequestBody String bizReqId, HttpServletResponse response) throws IOException {
        System.out.println(bizReqId);
        JSONObject jsonObj = JSONObject.parseObject(bizReqId);
        String addr = loginCallBackService.callBack(jsonObj.getString("bizCode"), jsonObj.getString("bizType"),"INSERT");
        JSONObject backData = HttpRequestTool.doRequestJson(addr, "POST",bizReqId);
        response.getWriter().write(backData.toJSONString());
        System.out.println(backData);
    }


    @RequestMapping(value = "/aabbcc/{id}",method = RequestMethod.GET)
    public String aa(@PathVariable String id, HttpServletResponse response) throws IOException {
        System.out.println(id);
        response.getWriter().write("nihaokkk");
//        System.out.println(getJson);
        return null;
    }
    @RequestMapping("/main")
    public String main(){
        return "index";
    }

    @RequestMapping("/login/addCall")
    public String addCall(Model model){
        int maxId = loginCallBackService.findMaxId();
        model.addAttribute("id",maxId++);
        return "addUrl";
    }

    @PostMapping("/login/call")
    public String call(Biz biz){
        System.out.println(biz);
        int maxId = loginCallBackService.findMaxId();
        biz.setId(++maxId);
        loginCallBackService.addBiz(biz);
        return "redirect:/user/main";
    }

}
