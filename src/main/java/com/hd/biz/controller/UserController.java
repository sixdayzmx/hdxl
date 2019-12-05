package com.hd.biz.controller;

import com.hd.biz.pojo.Biz;
import com.hd.biz.service.FindAllService;
import com.hd.biz.service.LoginCallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private FindAllService bizService;

    @RequestMapping("/login")
    public String login(String username,String pwd){
        if("zmx".equals(username)&&"123".equals(pwd)){
            return "redirect:/user/main";
        }
        return "index";
    }

    @RequestMapping("/main")
    public String loginMain(Model model){

        List<Biz> allBiz = bizService.findAllBiz();
        System.out.println(allBiz.toString());
        model.addAttribute("bizList",allBiz);
        return "main";
    }

}
