package com.sc.netty.service.impl;

import com.sc.netty.entity.FriendsRequest;
import com.sc.netty.mappers.FriendsRequestMapper;
import com.sc.netty.service.FriendsRequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 好友请求表 服务实现类
 * </p>
 *
 * @author Jeff
 * @since 2020-04-26
 */
@Service
public class FriendsRequestServiceImpl extends ServiceImpl<FriendsRequestMapper, FriendsRequest> implements FriendsRequestService {

}
