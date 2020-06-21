package com.woqiyounai.ioc.xml.demo5;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPost implements BeanPostProcessor {
    //创建对象前
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理器:--------"+beanName);
        return bean;
    }

    //创建对象后
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理器:--------"+beanName);
        return bean;
    }
}
