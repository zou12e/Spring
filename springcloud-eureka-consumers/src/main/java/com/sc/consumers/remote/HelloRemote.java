package com.sc.consumers.remote;

import com.sc.consumers.fallback.HelloRemoteFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * name: 远程服务名，及spring.application.name配置的名称
 * fallback: Fallback相当于是降级操作。
 *     对于查询操作, 我们可以实现一个fallback方法, 当请求后端服务出现异常的时候, 可以使用fallback方法返回的值。
 *     fallback方法的返回值一般是设置的默认值或者来自缓存。
 * 此类中的方法和远程服务中contoller中的方法名和参数需保持一致
 */

@FeignClient(name="spring-cloud-producer", fallback = HelloRemoteFallBack.class)
public interface HelloRemote {

    @RequestMapping(value = "/hello")
    String hello(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/hello2")
    String hello1();
}
