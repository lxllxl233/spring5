package com.woqiyounai.tx;

import com.woqiyounai.tx.annotation.service.UserService;
import com.woqiyounai.tx.xml.config.TxConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTx {

    //注解
    @Test
    public void testTx(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:tx/data.xml");
        UserService userService = context.getBean("userService",UserService.class);
        userService.accountOpt();
    }

    @Test
    public void testTxAnno(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:tx/data.xml");
        UserService userService = context.getBean("userService",UserService.class);
        userService.txAccountOpt();
    }

    // xml 配置
    @Test
    public void testTxXml(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:tx/xmlData.xml");
        com.woqiyounai.tx.xml.service.UserService userService =
                context.getBean("userService", com.woqiyounai.tx.xml.service.UserService.class);
        userService.txAccountOpt();
    }

    //配置类代替 xml
    @Test
    public void testTxXmlByConfig(){
        ApplicationContext context =
                new AnnotationConfigApplicationContext(TxConfig.class);
        com.woqiyounai.tx.xml.service.UserService userService =
                context.getBean("userService", com.woqiyounai.tx.xml.service.UserService.class);
        userService.txAccountOpt();
    }
}
