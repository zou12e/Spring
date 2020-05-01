package com.sc.ui;

import com.sc.config.SpringConfiguration;
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
  全注解配置
     @Configuration
        作用：指定当前类是一个配置类
             等同于 beanContext.xml
        细节：当配置类作为参数创建对象时，该注解可以不写
             ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class) ;

     @ComponentScan
        作用：用于通过注解指定spring在创建容器时要扫描的包
        属性：
            value：它和basePackages的作用一样，都是指定创建容器时要扫描的包
                   该注解等同于xml中配置的：
                    <context:component-scan base-package="com.sc"></context:component-scan>

    @Bean
        作用：用于把当前方法的返回值作为bean对象存入spring的ioc容器中
        属性：
             name：用于指定bean的id
        细节：
             当我们使用注解配置方法时，如果方法有参数，spring框架去容器中查找有没有可用的bean对象
             查找的方式和@Autowired注解的作用一样

    @Import
        作用：用于导入其他配置
        属性
            value：用于指定其他配置类的字节码
        @Configuration 主配置
        @Import        子配置


    @PropertySource
        作用：用于指定properties文件位置
        属性：
             value：指定文件名称和路径
                    关键字：classpath，表示类路径下


  使用Junit单元测试
    Spring整合junit的配置
        1. 导入spring整合junit jar

            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>5.2.5.RELEASE</version>

            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>

        2. 使用Junit提供一个注解把原有的main方法替换，替换成spring提供的
            @RunWith(SpringJUnit4ClassRunner.class)

        3. 告知spring容器 ioc创建基于xml还是注解
            @ContextConfiguration(classes = SpringConfiguration.class)
                locations：指定xml配置
                classes：  指定注解类配置

  当我们使用spring 5.x版本以上时，junit必须在4.12以上
 */

public class AnnotationClient {


    public static void main(String[] args) {


        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class) ;

        /**
         * 普通工厂的方法创建对象
         */
        IService beanService = context.getBean("customName", IService.class);
        beanService.save("customName");

        beanService.autowired("Bean");

    }
}
