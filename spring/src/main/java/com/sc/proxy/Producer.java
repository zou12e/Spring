package com.sc.proxy;

public class Producer implements  IProducer{

    public void sale(int money) {
        System.out.println("sale: " + money);
    }

    public void after(int money) {
        System.out.println("service: " + money);
    }



}
