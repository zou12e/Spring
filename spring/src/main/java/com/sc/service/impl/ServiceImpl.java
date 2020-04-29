package com.sc.service.impl;

import com.sc.factory.MyBeanFactory;
import com.sc.service.IService;

public class ServiceImpl implements IService {
    public void save(String name) {
        System.out.println("save:" + name);
    }

    public ServiceImpl getService() {
        System.out.println("我是通过普通工厂中的方法创建的对象");
        return new ServiceImpl();
    }

    public static ServiceImpl getStaticService() {
        System.out.println("我是通过普通工厂中的静态方法创建的对象");
        return new ServiceImpl();
    }
}
