package com.woqiyounai.ioc.xml.demo4;

import java.util.List;

public class Course {

    private List<Stu> stuList;

    public void setStuList(List<Stu> stuList) {
        this.stuList = stuList;
    }

    public void test(){
        System.out.println(stuList);
    }
}
