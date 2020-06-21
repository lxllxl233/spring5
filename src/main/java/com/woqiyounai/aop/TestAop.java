package com.woqiyounai.aop;

import com.woqiyounai.aop.annotation.User;
import com.woqiyounai.aop.xml.Book;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

    @Test
    public void annoTest(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:aop/bean1.xml");
        User user = context.getBean("user", User.class);
        user.add();
    }

    @Test
    public void xmlTest(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:aop/bean2.xml");
        Book book = context.getBean("book", Book.class);
        book.buy();
    }
}
