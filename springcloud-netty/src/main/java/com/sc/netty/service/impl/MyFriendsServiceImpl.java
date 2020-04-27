package com.sc.netty.service.impl;

import com.sc.netty.entity.MyFriends;
import com.sc.netty.mappers.MyFriendsMapper;
import com.sc.netty.service.MyFriendsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 我的朋友表 服务实现类
 * </p>
 *
 * @author Jeff
 * @since 2020-04-26
 */
@Service
public class MyFriendsServiceImpl extends ServiceImpl<MyFriendsMapper, MyFriends> implements MyFriendsService {

}
