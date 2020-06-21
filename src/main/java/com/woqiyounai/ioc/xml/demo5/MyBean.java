package com.woqiyounai.ioc.xml.demo5;

import com.woqiyounai.ioc.xml.demo4.Course;
import com.woqiyounai.ioc.xml.demo4.Stu;
import org.springframework.beans.factory.FactoryBean;

import java.util.ArrayList;

//普通类
public class MyBean implements FactoryBean<Course> {

    //定义类型和返回类型可以不一样
    public Course getObject() throws Exception {
        Course course = new Course();
        ArrayList<Stu> stus = new ArrayList<Stu>();
        course.setStuList(stus);
        return course;
    }

    public Class<?> getObjectType() {
        return null;
    }



    public boolean isSingleton() {
        return false;
    }
}
