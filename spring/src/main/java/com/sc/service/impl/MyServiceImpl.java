package com.sc.service.impl;

import com.sc.domain.Account;
import com.sc.service.IService;
import com.sc.utils.TransactionManager;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@NoArgsConstructor
@Component("customName")
@PropertySource("classpath:bean.properties")
public class MyServiceImpl implements IService {

    @Value("${service}")
    private String propertie;

    @Autowired
    private QueryRunner runner;

    @Autowired
    private  AutowiredImpl autowired;

    @Autowired
    private TransactionManager transactionManager;

    public MyServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    /**
     * 经常变化的数据，不适合注入方式
     */
    private String name;
    private Integer age;
    private Date birthday;

    private String[] myStrs;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String, String> myMap;
    private Properties myProps;


    public void save(String name) {
        System.out.println("save:" + name);
    }

    public void println(String name) {
        System.out.println("println:" + name);
        System.out.println("name: " + this.name);
        System.out.println("age: " + this.age);
        System.out.println("birthday: " + this.birthday);
    }

    public void print(String name) {
        System.out.println(Arrays.toString(myStrs));
        System.out.println(this.myList);
        System.out.println(this.mySet);
        System.out.println(this.myMap);
        System.out.println(this.myProps);

    }

    public void autowired(String name) {
        System.out.println("autowired:" + name);
        System.out.println("propertie:" + propertie);
        autowired.println();
    }


    public void transfer() {
        try {
            // 1. 开启事务
            transactionManager.beginTransaction();
            // 2. 执行操作
            List<Account> list = runner.query("select * from account", new BeanListHandler<Account>(Account.class));
            // 3. 提交事务
            transactionManager.commit();
            // 4. 返回结果
            list.forEach(System.out::println);
        } catch (Exception e) {
            // 5. 回滚操作
            transactionManager.rollback();
        } finally {
            // 6. 释放连接
            transactionManager.release();
        }
    }

    @Override
    public List<Account> proxy()  {
        try {
            List<Account> list = runner.query("select * from account", new BeanListHandler<Account>(Account.class));
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void text1() {
        System.out.println("text1");
    }

    @Override
    public void text2(Integer i) {
        System.out.println("text2");
    }

    @Override
    public int text3() {
        System.out.println("text3");
        return 0;
    }

    @Override
    public int text4(Integer i) {
        System.out.println("text4");
        return 0;
    }

    public MyServiceImpl getService() {
        System.out.println("我是通过普通工厂中的方法创建的对象");
        return new MyServiceImpl();
    }

    public static MyServiceImpl getStaticService() {
        System.out.println("我是通过普通工厂中的静态方法创建的对象");
        return new MyServiceImpl();
    }
}
