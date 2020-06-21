package com.woqiyounai.sp5new.generic;

import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;

public class TestGeneric {

    @Test
    public void testGeneric(){
        GenericApplicationContext context = new GenericApplicationContext();
        // 调用 context 的方法对象注册
        context.refresh();
        //注册对象
        context.registerBean(User.class,() -> new User());
        //获取注册的对象
        User bean = context.getBean(User.class);
        System.out.println(bean);
    }
}
