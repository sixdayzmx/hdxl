package com.hd.biz.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


/**
 * 类名: HttpRequestTool
 * 描述: 网络请求工具类
 */
public class HttpRequestTool {

    private static Logger logger = LoggerFactory.getLogger(HttpRequestTool.class);

    public static JSONObject doRequestJson(String requestUrl) {
        return doRequestJson(requestUrl, "GET", null);
    }

    /**
     * 发起http请求并获取结果("content-type", "application/json")
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject doRequestJson(String requestUrl, String requestMethod, String outputStr) {

        //定以一个json对象
        JSONObject jsonObject;
        //定以一个StringBuilder对象
        StringBuilder builder = new StringBuilder();
        //选择一个输入流
        InputStream inputStream = null;
        try {
            //new一个URl的对象
            URL url = new URL(requestUrl);
            //创建一个URLConnection的实例但是创建成功时并不会建立连接而是在使用connect()方法后才会连接
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            //URL连接可用于输入和/或输出。 如果您打算使用URL连接输出，请将DoOutput标志设置为true，否则返回false。 默认值为false。
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            //如果连接上的UseCaches标志为true，则允许连接使用任何可以缓存的缓存。 如果是false，缓存将被忽略
            httpUrlConn.setUseCaches(false);
            //设置请求头中参数的类型
            httpUrlConn.setRequestProperty("content-type", "application/json");
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            //如果请求参数是GET就直接连接了
            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                //当有数据时定义一个输出流：
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes(StandardCharsets.UTF_8));
                outputStream.close();
            }
            //将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.parseObject(builder.toString());

        } catch (ConnectException ce) {

            logger.error("server connection timed out, " + ce.getMessage());

            return null;

        } catch (Exception e) {

            logger.error(e.getMessage());
            return null;

        } finally {

            try {

                if (inputStream != null) {
                    inputStream.close();
                }

            } catch (IOException e) {
                logger.error(e.getMessage());
            }

        }

        return jsonObject;
    }

}
