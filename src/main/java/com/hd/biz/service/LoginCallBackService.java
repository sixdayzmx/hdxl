package com.hd.biz.service;

import com.hd.biz.pojo.Biz;

import java.util.List;

public interface LoginCallBackService {
    public String callBack(String code,String type,String addrType);
    public int findMaxId();
    public Integer addBiz(Biz biz);
}
