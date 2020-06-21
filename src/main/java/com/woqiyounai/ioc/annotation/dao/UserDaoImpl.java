package com.woqiyounai.ioc.annotation.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
    public void add() {
        System.out.println("添加");
    }
}
