package com.sc.netty.service.impl;

import com.sc.netty.entity.ChatMsg;
import com.sc.netty.mappers.ChatMsgMapper;
import com.sc.netty.service.ChatMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息表 服务实现类
 * </p>
 *
 * @author Jeff
 * @since 2020-04-26
 */
@Service
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgMapper, ChatMsg> implements ChatMsgService {

}
