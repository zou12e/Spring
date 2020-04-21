package com.sc.producer.controller;

import com.sc.api.IFeignProducerServiceClient;
import com.sc.producer.dto.EntityCopyMapper;
import com.sc.producer.entity.User;
import com.sc.producer.mappers.UserMapper;
import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RefreshScope
public class IFeignUserController implements IFeignProducerServiceClient {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public UserDTO login(@RequestBody UserVO user) {
        User loginUser = userMapper.login(EntityCopyMapper.INSTANCE.toUser(user));
        stringRedisTemplate.opsForValue().set(String.valueOf(loginUser.getId()), loginUser.getName());
        return EntityCopyMapper.INSTANCE.toUserDTO(loginUser);
    }

    public List<UserDTO> getUsers() {
        return EntityCopyMapper.INSTANCE.toListUserDTO(userMapper.selectList(null));
    }


    public UserDTO getUser(@PathVariable("id") Integer id) {
        return EntityCopyMapper.INSTANCE.toUserDTO(userMapper.getUser(id));
    }


    public UserDTO getUserById(@RequestParam("id")  Integer id) {
        return EntityCopyMapper.INSTANCE.toUserDTO(userMapper.getUser(id));
    }

}
