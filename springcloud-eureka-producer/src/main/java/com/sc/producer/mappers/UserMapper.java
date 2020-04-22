package com.sc.producer.mappers;

import com.sc.producer.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jeff
 * @since 2020-04-22
 */
public interface UserMapper extends BaseMapper<User> {
    User login(User toUser);

    User getUser(Integer id);
}
