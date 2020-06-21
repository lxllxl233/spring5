package com.woqiyounai.ioc.xml.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterCreateBook {

    @Test
    public void setterCreateBook(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc/xml/book.xml");
        Book book = context.getBean("book", Book.class);
        System.out.println(book.getBeanAuthor());
        System.out.println(book.getBeanName());
    }
}
