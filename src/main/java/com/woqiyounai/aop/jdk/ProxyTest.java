package com.woqiyounai.aop.jdk;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        ProxyFactory proxyFactory = new ProxyFactory(userDao);
        Class[] interfaces = {UserDao.class};
        UserDao o = (UserDao)Proxy.newProxyInstance(UserDaoImpl.class.getClassLoader(), interfaces, proxyFactory);
        String id = o.update("id");
        System.out.println(id);
    }
}
