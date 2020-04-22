package com.sc.producer.controller;


import com.sc.producer.entity.User;
import com.sc.producer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jeff
 * @since 2020-04-22
 */
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//
//}


/**
 * @author ${author}
 * @since ${date}
 */
@Api(description = "user")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("列表")
    @GetMapping("/list")
    public List<User> list() {
        try {
            return userService.list();
        } catch (Exception e) {
            log.error("list error", e);
            return null;
        }
    }


}
