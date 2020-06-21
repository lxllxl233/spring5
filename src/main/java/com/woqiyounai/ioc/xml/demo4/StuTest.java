package com.woqiyounai.ioc.xml.demo4;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StuTest {
    @Test
    public void stuTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc/xml/di.xml");
        Stu stu = context.getBean("stu", Stu.class);
        stu.test();
    }

    @Test
    public void stuTester(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc/xml/course.xml");
        Course course = context.getBean("course", Course.class);
        course.test();
    }
}
