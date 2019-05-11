package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.AdminUserMapper;
import com.how2java.tmall.pojo.AdminUser;
import com.how2java.tmall.pojo.AdminUserExample;
import com.how2java.tmall.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    AdminUserMapper adminUserMapper;
    @Override
    public AdminUser get(String name, String password) {
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        return adminUserMapper.selectByExample(example).get(0);
    }
}
