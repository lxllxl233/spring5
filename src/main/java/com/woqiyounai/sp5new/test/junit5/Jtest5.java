package com.woqiyounai.sp5new.test.junit5;


import com.woqiyounai.tx.annotation.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:tx/data.xml")//加载配置文件
public class Jtest5 {

    @Autowired
    UserService userService;
    @Test
    public void test5(){
        System.out.println(userService);
    }
}
