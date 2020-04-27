package com.sc.netty.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sc.netty.entity.User;
import com.sc.netty.mappers.UserMapper;
import com.sc.netty.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Jeff
 * @since 2020-04-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUserByNickName(String nickname) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(User.NICKNAME, nickname);
        return this.getOne(wrapper);
    }
}
