package com.how2java.tmall.service;

import com.how2java.tmall.pojo.AdminUser;

public interface AdminUserService {
    AdminUser get(String name, String password);
}
