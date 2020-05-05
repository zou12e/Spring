package com.sc.ui;

import com.sc.config.SpringConfiguration;
import com.sc.domain.Account;
import com.sc.service.IService;
import com.sc.service.ISpringService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 */

public class SpringAnnotationClient {


    public static void main(String[] args) {


        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class) ;

        ISpringService beanService = context.getBean("springServiceImpl", ISpringService.class);

        beanService.transfer();

    }
}
