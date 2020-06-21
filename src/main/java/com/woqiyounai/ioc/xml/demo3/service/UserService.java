package com.woqiyounai.ioc.xml.demo3.service;

import com.woqiyounai.ioc.xml.demo3.dao.UserDao;

public class UserService {
    //创建 UserDao 类型属性，生成 set 方法
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){
        System.out.println("add .......");
        userDao.update();
    }
}
