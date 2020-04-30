package com.sc.service.impl;

import org.springframework.stereotype.Service;

@Service
public class AutowiredImpl {

    public void println() {
        System.out.println("Autowired注入方法");
    }
}
