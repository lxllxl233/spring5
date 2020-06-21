package com.woqiyounai.ioc.xml.demo5;

import com.woqiyounai.ioc.xml.demo4.Course;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFacytory {

    @Test
    public void testFactory(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc/xml/factory.xml");
        //返回类型可以和定义类型不一致
        Course course1 = context.getBean("myBean", Course.class);
        Course course2 = context.getBean("myBean", Course.class);
        System.out.println(course1);
        System.out.println(course2);
    }

    @Test
    public void testLifecycle(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc/xml/factory.xml");
        //返回类型可以和定义类型不一致
        Orders myBean = context.getBean("orders", Orders.class);
        System.out.println(myBean);
        //手动销毁
        ((ClassPathXmlApplicationContext)context).close();
    }
}
