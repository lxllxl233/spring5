package com.woqiyounai.sp5new.test.junit4;

import com.woqiyounai.tx.annotation.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//指定 Junit 版本
@RunWith(SpringJUnit4ClassRunner.class)//单元测试框架版本
@ContextConfiguration("classpath:tx/data.xml")//加载配置文件
public class Jtest4 {

    @Autowired
    UserService userService;

    @Test
    public void test(){
        System.out.println(userService);
    }

}
