package com.sc.consumers.fallback;

import com.sc.consumers.remote.HelloRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;


@Component
public class HelloRemoteFallBack implements HelloRemote {
    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "hello " + name + ", i am fallback massage";
    }

    @Override
    public String hello1() {
        return "error";
    }
}