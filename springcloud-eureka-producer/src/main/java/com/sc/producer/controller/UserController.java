package com.sc.producer.controller;

import com.sc.producer.mapper.UserMapper;
import com.sc.producer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @Autowired
    UserMapper userMapper;

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userMapper.getAll();
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userMapper.getUser(id);
    }

    @PostMapping("/add")
    public Long save(@RequestBody User user) {
        return userMapper.insertUser(user);
    }

    @PostMapping("/update")
    public Long update(@RequestBody User user) {
        return userMapper.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable("id") Integer id) {
        return userMapper.deleteUser(id);
    }
}
