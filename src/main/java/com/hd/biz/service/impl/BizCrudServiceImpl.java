package com.hd.biz.service.impl;

import com.hd.biz.mapper.BizMapper;
import com.hd.biz.service.BizCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BizCrudServiceImpl implements BizCrudService {
    @Autowired
    private BizMapper mapper;

    @Override
    public Integer deleteById(int id) {
        return mapper.deleteById(id);
    }
}
