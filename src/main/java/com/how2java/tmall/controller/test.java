package com.how2java.tmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class test {
    @RequestMapping("111")
    public String a() {
        return "redirect:222?oid=111";
    }
    @RequestMapping("222")
    public String b() {
        return "redirect:333?id=222";
    }
    @RequestMapping("333")
    public String c() {
        return "fore/home";
    }
}
