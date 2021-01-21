package com.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MyController {
    @RequestMapping(value = {"/","index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello,shiro");
        return "index";
    }
}
