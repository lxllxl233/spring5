package com.woqiyounai.ioc.xml.demo2;

public class Order {

    private String oname;
    private String address;
    private String name;
    private String other;

    public Order(String oname, String address,String name,String other) {
        this.oname = oname;
        this.address = address;
        this.name = name;
        this.other = other;
    }

    public String getOname() {
        return oname;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getOther() {
        return other;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oname='" + oname + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
