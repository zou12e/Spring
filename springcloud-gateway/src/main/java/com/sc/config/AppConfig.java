package com.sc.config;

import com.sc.filter.factory.JwtCheckGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化Spring容器
 *
 * 1.1、@Configuration配置spring并启动spring容器
 * 1.2、@Configuration启动容器+@Bean注册Bean
 * 1.3、@Configuration启动容器+@Component注册Bean
 * 1.4、使用 AnnotationConfigApplicationContext 注册 AppContext 类的两种方法
 * 1.5、配置Web应用程序(web.xml中配置AnnotationConfigApplicationContext)
 */
@Configuration
public class AppConfig {

    @Bean
    public JwtCheckGatewayFilterFactory jwtCheckGatewayFilterFactory(){
        return new JwtCheckGatewayFilterFactory();
    }
}
