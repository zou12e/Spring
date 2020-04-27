package com.sc.netty.service;

import com.sc.netty.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Jeff
 * @since 2020-04-26
 */
public interface UserService extends IService<User> {

    User getUserByNickName(String nickname);
}
