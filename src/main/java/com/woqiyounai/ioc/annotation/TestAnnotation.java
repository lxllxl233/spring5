package com.woqiyounai.ioc.annotation;

import com.woqiyounai.ioc.annotation.config.SpringConfig;
import com.woqiyounai.ioc.annotation.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotation {

    @Test
    public void testService(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:ioc/annotation/scan.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
    }


    //测试 @Qualifier 注解
    @Test
    public void testQualifier(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:ioc/annotation/scan.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }

    //完全注解开发
    @Test
    public void testQualifierAnnotation(){
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }
}
