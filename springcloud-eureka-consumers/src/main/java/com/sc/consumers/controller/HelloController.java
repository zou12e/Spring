package com.sc.consumers.controller;

import com.sc.consumers.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: shiyao.wei
 * @Date: 2019/7/2 11:25
 * @Version: 1.0
 * @Desc:
 */
@RestController
public class HelloController {

    @Autowired
    HelloRemote helloRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.hello(name);
    }

    @RequestMapping("/hello1")
    public String index1() {
        return helloRemote.hello1();
    }



}
