package com.woqiyounai.ioc.xml.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ControCreateTest {

    @Test
    public void controCreatetest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc/xml/order.xml");
        Order order = context.getBean("order",Order.class);
        System.out.println(order);
    }
}
