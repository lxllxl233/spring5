package com.woqiyounai.aop.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//代理增强对象
public class ProxyFactory implements InvocationHandler {

    private Object obj;

    public ProxyFactory(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        o = obj;
        System.out.println("执行方法前");
        Object invoke = method.invoke(o, objects);
        System.out.println("执行方法后");
        return invoke;
    }
}
