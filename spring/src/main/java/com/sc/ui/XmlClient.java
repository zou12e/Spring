package com.sc.ui;

import com.sc.factory.MyBeanFactory;
import com.sc.service.IService;
import com.sc.service.impl.MyServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class XmlClient {

    /**
     * 创建对象的方式与依赖注入
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 普通new()对象
         */
        IService service = new MyServiceImpl();
        service.save("new()");

        /**
         * 自己实现BeanFactory
         */
        IService serviceBean = (IService) MyBeanFactory.getBean("service");
        serviceBean.save("MyBeanFactory");



        /**
         *  ClassPathXmlApplicationContext 类路径配置文件
         *  FileSystemXmlApplicationContext 磁盘可访问路径的配置文件
         *  AnnotationConfigApplicationContext 注解
        */
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml") ;
        IService xmlBean = context.getBean("service", IService.class);
        xmlBean.save("xmlService");

        /**
         * ApplicationContext    单例对象适用
         *  构建核心容器时 采用立即加载模式，读取文件后马上创建对象
         *
         * BeanFactory           多例对象适用
         *  构建核心容器时 采用延迟加载的模式，什么时候获取对象，才真正创建对象
         */
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory beanFactory =  new XmlBeanFactory(resource);
        IService xmlBeanFactory = beanFactory.getBean("service", IService.class);
        xmlBeanFactory.save("xmlBeanFactoryService");

        /**
         * 普通工厂的方法创建对象
         */
        IService beanService = context.getBean("beanService", IService.class);
        beanService.save("beanService");


        /**
         * 普通工厂的静态方法创建对象
         */
        IService staticBeanService = context.getBean("staticService", IService.class);
        staticBeanService.save("staticService");


        /**
         * 构造函数依赖注入
         */
        IService constructorService = context.getBean("constructorService", IService.class);
        constructorService.println("constructorService");


        /**
         * set方法依赖注入
         */
        IService setService = context.getBean("setService", IService.class);
        setService.println("setService");


        /**
         * set方法依赖注入集合
         */
        IService collectionService = context.getBean("collectionService", IService.class);
        collectionService.print("collectionService");
    }
}
