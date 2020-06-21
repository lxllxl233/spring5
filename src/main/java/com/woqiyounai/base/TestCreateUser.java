package com.woqiyounai.base;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCreateUser {
    @Test
    public void testBaseA(){
        //加载Spring的配置文件 , 用context上下文来获取
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc/xml/user.xml");
        //获取配置创建的对象
        User user = context.getBean("user", User.class);
        user.add();
    }

}
