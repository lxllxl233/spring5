package com.woqiyounai.ioc.annotation.dao;

import org.springframework.stereotype.Repository;

@Repository("UserDaoImplCopy")
public class UserDaoImplCopy implements UserDao{
    public void add() {
        System.out.println("UserDaoImplCopy add.......................");
    }
}
