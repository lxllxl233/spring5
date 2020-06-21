package com.woqiyounai.ioc.xml.demo6;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Emp {

    private Dept dept;

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                '}';
    }

    @Test
    public void test(){
        System.out.println(dept);
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc/xml/autowired.xml");
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }
}
