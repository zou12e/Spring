package com.sc.factory;

import com.sc.service.IService;
import com.sc.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 */
@Component
public class BeanFactory {

    @Autowired
    private TransactionManager transactionManager;

    /**
     * 用于创建Service的代理工厂
     */

    @Autowired
    private IService iservice;

    @Bean("proxyIService")
    public IService getService() {
        return (IService) Proxy.newProxyInstance(iservice.getClass().getClassLoader(),
                iservice.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtValue = null;
                        try {
                            // 1. 开启事务
                            transactionManager.beginTransaction();
                            // 2. 执行操作
                            rtValue = method.invoke(iservice, args);
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
                        return rtValue;
                    }
                });
    }

}
