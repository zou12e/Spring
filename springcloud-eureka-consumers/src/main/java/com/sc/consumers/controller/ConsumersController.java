package com.sc.consumers.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sc.constant.IProducerServiceUrl;
import com.sc.service.user.ProducerService;
import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class ConsumersController {

    @Autowired
    ProducerService producerService;

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserVO vo) {
        return producerService.login(vo);
    }

    @RequestMapping("/userList")
    public List<UserDTO> userList() {
        return producerService.getUsers();
    }


    @RequestMapping("/user/{id}")
    @HystrixCommand(
            threadPoolKey = "timeout", // 线程池名称 服务隔离
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"), // 线程数
                    @HystrixProperty(name = "maxQueueSize", value = "10") // 排队数量
            },
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5"), // 超时时间
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="10000"),// 时间窗长度
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"), // 最小请求数量
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000"), // 活动窗口时间长度
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="5"), // 失败率百分比

            },
            fallbackMethod = "user2" // 降级回调
    )
    public UserDTO user(@PathVariable("id") Integer id) {
        return producerService.getUser(id);
    }

    public UserDTO user2(@PathVariable("id") Integer id) {
        UserDTO dto = new UserDTO();
        dto.setName("fallbackMethod");
        return dto;
    }

    @RequestMapping("/userById")
    public UserDTO userById(@ModelAttribute UserVO vo) {
        return producerService.getUserById(vo);
    }

}
