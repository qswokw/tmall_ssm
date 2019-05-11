package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.AdminUser;
import com.how2java.tmall.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;
    @RequestMapping("adminlogin")
    public String adminlogin(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        AdminUser adminUser = adminUserService.get(name, password);
        if (adminUser == null) {
            model.addAttribute("msg", "账户密码错误");
            return "admin/login";
        }
        session.setAttribute("adminuser", adminUser);
        return "redirect:admin_category_list";
    }
}
