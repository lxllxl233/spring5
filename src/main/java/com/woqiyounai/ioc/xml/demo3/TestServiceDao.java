package com.woqiyounai.ioc.xml.demo3;

import com.woqiyounai.ioc.xml.demo3.bean.Emp;
import com.woqiyounai.ioc.xml.demo3.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestServiceDao {

    @Test
    public void testServiceDao(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc/xml/userDao.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }

    //测试内部 eban
    @Test
    public void intiBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc/xml/relationship.xml");
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }
}
