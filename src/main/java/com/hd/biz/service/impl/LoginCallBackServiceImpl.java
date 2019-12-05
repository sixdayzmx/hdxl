package com.hd.biz.service.impl;

import com.hd.biz.mapper.BizMapper;
import com.hd.biz.pojo.Biz;
import com.hd.biz.service.LoginCallBackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginCallBackServiceImpl implements LoginCallBackService {
    @Autowired
    private BizMapper bizMapper;
    @Override
    public String callBack(String code,String type,String addrType) {
        Map<String,Object> columnMap=new HashMap<String,Object>();
        columnMap.put("bizCode",code);
        String callBackAddr;
        callBackAddr = bizMapper.findAddr(code,type,addrType);
        return callBackAddr;

    }

    @Override
    public int findMaxId() {

        return bizMapper.findMaxId();
    }

    @Override
    public Integer addBiz(Biz biz) {
        return bizMapper.addBiz(biz);
    }

}
