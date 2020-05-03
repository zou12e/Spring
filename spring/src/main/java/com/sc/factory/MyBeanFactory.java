package com.sc.factory;

import com.sc.domain.Account;
import com.sc.service.IService;
import com.sc.utils.TransactionManager;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 *
 */
public class MyBeanFactory {
    private static Properties props;

    //定义MAP 存放我们需要创建对象，称为容器
    private static Map<String, Object> beans;
    static {
        try {
            props = new Properties();
            InputStream in = MyBeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);

            beans = new HashMap<String, Object>();
            Enumeration keys = props.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                String beanPath = props.getProperty(key);
                Object value = Class.forName(beanPath).newInstance();
                beans.put(key, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据bean名称获取bean对象
     * @param beanName
     * @return
     */
//    public static Object getBean(String beanName) {
//        Object bean = null;
//        try {
//            String beanPath = props.getProperty(beanName);
//            bean = Class.forName(beanPath).newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bean;
//    }

    /**
     * 根据bean名称获取bean对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }


    @Autowired
    private TransactionManager transactionManager;

    /**
     * 用于创建Service的代理工厂
     */

    private IService iService;

    public IService getService() {
        Proxy.newProxyInstance(iService.getClass().getClassLoader(),
                iService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        try {
                            // 1. 开启事务
                            transactionManager.beginTransaction();
                            // 2. 执行操作
                            method.invoke(iService, args);

                            // 3. 提交事务
                            transactionManager.commit();
                            // 4. 返回结果

                        } catch (Exception e) {
                            // 5. 回滚操作
                            transactionManager.rollback();
                        } finally {
                            // 6. 释放连接
                            transactionManager.release();
                        }
                    }
                });
    }

}
