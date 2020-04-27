package com.sc.netty.service.impl;

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

}
