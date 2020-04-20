package com.sc.consumers.controller;

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
    public UserDTO user(@PathVariable("id") Integer id) {
        return producerService.getUser(id);
    }

    @RequestMapping("/userById")
    public UserDTO userById(@ModelAttribute UserVO vo) {
        return producerService.getUserById(vo);
    }

}
