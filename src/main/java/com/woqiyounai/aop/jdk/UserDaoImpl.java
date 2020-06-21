package com.woqiyounai.aop.jdk;

public class UserDaoImpl implements UserDao{
    public int add(int a, int b) {
        return a + b;
    }

    public String update(String id) {
        return id;
    }
}
