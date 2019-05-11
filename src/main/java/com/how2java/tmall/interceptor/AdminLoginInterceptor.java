package com.how2java.tmall.interceptor;


import com.how2java.tmall.pojo.AdminUser;
import com.how2java.tmall.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class AdminLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String[] noNeedAuthPage = new String[]{
                "admin_category_list",
                "admin_order_list"
        };
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
        System.out.println(uri);
        String method = StringUtils.substringAfterLast(uri, "/");
        System.out.println(method);
        if (Arrays.asList(noNeedAuthPage).contains(method)) {
             AdminUser adminuser= (AdminUser) session.getAttribute("adminuser");
            if (adminuser == null) {
                response.sendRedirect("adminloginPage");
                return false;
            }
        }
        return true;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
