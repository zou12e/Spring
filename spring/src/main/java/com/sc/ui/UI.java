package com.sc.ui;

import com.sc.factory.MyBeanFactory;
import com.sc.service.IService;
import com.sc.service.impl.ServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import sun.tools.jconsole.inspector.XMBean;
import org.springframework.core.io.Resource;


public class UI {


    public static void main(String[] args) {

        IService service = new ServiceImpl();
        service.save("new()");

        IService serviceBean = (IService) MyBeanFactory.getBean("service");
        serviceBean.save("MyBeanFactory");



        /**
         * ClassPathXmlApplicationContext 类路径配置文件
         *  FileSystemXmlApplicationContext 磁盘可访问路径的配置文件
         *  AnnotationConfigApplicationContext 注解
        */
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml") ;
        IService xmlBean = context.getBean("xmlService", IService.class);
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
        IService xmlBeanFactory = beanFactory.getBean("xmlService", IService.class);
        xmlBeanFactory.save("beanFactoryXml");


        IService beanService = context.getBean("beanService", IService.class);
        beanService.save("beanService");

        IService staticBeanService = context.getBean("staticService", IService.class);
        staticBeanService.save("staticService");

    }
}
