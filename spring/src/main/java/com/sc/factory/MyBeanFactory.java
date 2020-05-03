package com.sc.factory;

import com.sc.domain.Account;
import com.sc.service.IService;
import com.sc.utils.TransactionManager;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

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
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }

}
