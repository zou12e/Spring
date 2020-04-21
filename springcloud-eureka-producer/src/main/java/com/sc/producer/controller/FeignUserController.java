package com.sc.producer.controller;

import com.sc.api.IProducerService;
import com.sc.producer.dto.EntityCopyMapper;
import com.sc.producer.mapper.UserMapper;
import com.sc.producer.model.User;
import com.sc.constant.IProducerServiceUrl;
import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RefreshScope
public class FeignUserController implements IProducerService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping(IProducerServiceUrl.LOGIN)
    public UserDTO login(@RequestBody UserVO user) {
        User loginUser = userMapper.login(EntityCopyMapper.INSTANCE.toUser(user));
        stringRedisTemplate.opsForValue().set(String.valueOf(loginUser.getId()), loginUser.getName());
        return EntityCopyMapper.INSTANCE.toUserDTO(loginUser);
    }

    @GetMapping(IProducerServiceUrl.GETUSERS)
    public List<UserDTO> getUsers() {
        return EntityCopyMapper.INSTANCE.toListUserDTO(userMapper.selectList(null));
    }

    @GetMapping(IProducerServiceUrl.GETUSER)
    public UserDTO getUser(@PathVariable("id") Integer id) {
        return EntityCopyMapper.INSTANCE.toUserDTO(userMapper.getUser(id));
    }

    @GetMapping(IProducerServiceUrl.GETUSERBYID)
    public UserDTO getUserById(@ModelAttribute UserVO user) {
        return EntityCopyMapper.INSTANCE.toUserDTO(userMapper.getUser(user.getId()));
    }

    @PostMapping("/add")
    public Integer save(@RequestBody User user) {
        return userMapper.insert(user);
    }

    @PostMapping("/update")
    public Integer update(@RequestBody User user) {
        return userMapper.updateById(user);
    }

    @DeleteMapping("/delete/{id}")
    public Integer delete(@PathVariable("id") Integer id) {
        return userMapper.deleteById(id);
    }
}
