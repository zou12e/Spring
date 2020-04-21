package com.sc.producer.service.impl;

import com.sc.producer.entity.User;
import com.sc.producer.mappers.UserMapper;
import com.sc.producer.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jeff
 * @since 2020-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
