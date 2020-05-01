package com.sc.config;


import com.sc.service.IService;
import com.sc.service.impl.AutowiredImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan( "com.sc")
public class SpringConfiguration {


    @Bean
    public AutowiredImpl autowired() {
        return new AutowiredImpl();
    }


}
