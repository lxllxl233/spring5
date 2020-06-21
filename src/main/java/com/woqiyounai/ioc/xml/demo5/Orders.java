package com.woqiyounai.ioc.xml.demo5;

//演示 bean 的生命周期
public class Orders {
    private String oname;

    public Orders( ) {
        System.out.println("无参构造");
    }

    public void setOname(String oname) {
        System.out.println("调用了 setter 方法");
        this.oname = oname;
    }

    //创建执行的初始化方法
    public void init(){
        System.out.println("执行初始化方法");
    }

    //销毁方法
    public void destroy(){
        System.out.println("调用了销毁方法");
    }
}
