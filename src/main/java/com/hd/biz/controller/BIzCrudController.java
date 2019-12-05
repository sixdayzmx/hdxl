package com.hd.biz.controller;

import com.hd.biz.service.BizCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BIzCrudController {
    @Autowired
    private BizCrudService bizCrudService;

    @RequestMapping("/biz/{id}")
    public String delete(@PathVariable int id){

        System.out.println(id);
        bizCrudService.deleteById(id);

        return "redirect:/user/main";
    }
}
