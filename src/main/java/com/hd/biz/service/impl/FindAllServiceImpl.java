package com.hd.biz.service.impl;

import com.hd.biz.mapper.BizMapper;
import com.hd.biz.pojo.Biz;
import com.hd.biz.service.FindAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllServiceImpl implements FindAllService {

    @Autowired
    BizMapper bizMapper;

    @Override
    public List<Biz> findAllBiz() {
        return bizMapper.findAllBiz();
    }
}
