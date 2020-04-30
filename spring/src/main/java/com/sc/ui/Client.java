package com.sc.ui;

import com.sc.factory.MyBeanFactory;
import com.sc.service.IService;
import com.sc.service.impl.ServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
  注解类型
    1. 创建对象  (xml <bean>)
        @Component
            作用：用于把当前类对象存入spring容器中
            属性：value 指定bean的id
        @Controller   表现层
        @Service      业务层
        @Repository   持久层
    2. 注入数据  (xml <property>)
        @Autowired
            作用：自动按照类型注入，只要容器中有唯一的一个bean对象，就能注入成功
                 如果ioc容器中没有bean，则报错
                 如果ioc容器有多个bean
            位置：
                变量上，或者方法上
        @Qualifier 配合@Autowired使用，选择多个bean中的id
            属性： value指定bean的id
        @Resource 直接按照bean的id注入， 可单独使用
            属性： name指定bean的id

        ===================以上注入bean类型，集合只能xml注入================================
        @Value
            作用： 注入基本类型与String类型
            属性： value指定属性的值，可以使用SpEL表达式 ${表达式}

    3. 改变作用域范围  (xml scope)
        @Scope("singleton") 默认单例
        @Scope("prototype") 多例

    4. 生命周期  (xml init-method destroy-method)
        @PostConstruct 初始化
        @PreDestroy    销毁
 */

public class Client {

    /**
     * 创建对象的方式与依赖注入
     * @param args
     */
    public static void main(String[] args) {

        /**
         *  ClassPathXmlApplicationContext 类路径配置文件
         *  FileSystemXmlApplicationContext 磁盘可访问路径的配置文件
         *  AnnotationConfigApplicationContext 注解
        */
        ApplicationContext context = new ClassPathXmlApplicationContext("beanContext.xml") ;


        /**
         * 普通工厂的方法创建对象
         */
        IService beanService = context.getBean("customName", IService.class);
        beanService.save("customName");


        beanService.autowired("Autowired");

    }
}
