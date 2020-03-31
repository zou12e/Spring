package com.sc.producer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RefreshScope
public class UserController {

    @Value("${mode}")
    private String mode;

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hello "+name+"，producer is ready";
    }

    @RequestMapping("/hello1")
    public String hello1() {
        return "hello producer is ready";
    }


    @RequestMapping("/hello2")
    public String from() {
        return this.mode;
    }
}
