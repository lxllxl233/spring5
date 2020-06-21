package com.woqiyounai.ioc.annotation.service;

import com.woqiyounai.ioc.annotation.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

//    @Qualifier("UserDaoImplCopy")
//    @Autowired


    @Value("abc")
    private String name;

    @Resource(name = "UserDaoImplCopy")
    UserDao userDao;

    public void add(){
        System.out.println("service ................");
        userDao.add();
        System.out.println(name);
    }
}
