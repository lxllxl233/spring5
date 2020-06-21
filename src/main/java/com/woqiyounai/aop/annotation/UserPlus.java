package com.woqiyounai.aop.annotation;

//增强对象

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//增强类上加注解 @Aspect ，生成代理对象
@Aspect
@Component
public class UserPlus {

    @Pointcut("execution(* com.woqiyounai.aop.annotation.User.add(..))")
    public void pointDemo(){

    }

    //表示前置通知
//    @Before("execution(* com.woqiyounai.aop.annotation.User.add(..))")
    @Before(value = "pointDemo()")
    public void before(){
        System.out.println("前置通知：add before ... ...");
    }

    //表示后置通知
//    @After("execution(* com.woqiyounai.aop.annotation.User.add(..))")
    @After(value = "pointDemo()")
    public void after(){
        System.out.println("后置通知：add  after ... ...");
    }

    //表示后置通知
//    @AfterReturning("execution(* com.woqiyounai.aop.annotation.User.add(..))")
    @AfterReturning(value = "pointDemo()")
    public void afterReturning(){
        System.out.println("最终通知：add  afterReturning ... ...");
    }

    //表示后置通知
//    @AfterThrowing("execution(* com.woqiyounai.aop.annotation.User.add(..))")
    @AfterThrowing(value = "pointDemo()")
    public void afterThrowing(){
        System.out.println("异常通知：add  afterThrowing ... ...");
    }

    //表示后置通知
//    @Around("execution(* com.woqiyounai.aop.annotation.User.add(..))")
    @Around(value = "pointDemo()")
    public void around(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("环绕通知之前：add  around ... ...");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕通知之后：add  around ... ...");
    }
}
