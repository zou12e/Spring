package com.sc.gateway.remote;

import com.sc.gateway.fallback.HelloRemoteFallBack;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="spring-cloud-producer", fallback = HelloRemoteFallBack.class)
public interface HelloRemote {

    @RequestMapping(value = "/hello")
    String hello(@RequestParam(value = "name") String name);


    @RequestMapping(value = "/hello2")
    String hello1();
}
