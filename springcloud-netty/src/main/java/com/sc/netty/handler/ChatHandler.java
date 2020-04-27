package com.sc.netty.handler;

import com.alibaba.fastjson.JSON;
import com.sc.netty.entity.ChatMsg;
import com.sc.netty.entity.User;
import com.sc.netty.service.ChatMsgService;
import com.sc.netty.service.UserService;
import com.sc.netty.utils.SpringUtil;
import com.sc.netty.websoket.data.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * 处理消息的助手类
 * TextWebSocketFrame websocket专用的文门处理对象, frame是消息的载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 客户端client
     */
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        UserService userService = (UserService)SpringUtil.getBean("userServiceImpl");
        ChatMsgService chatMsgService  = (ChatMsgService)SpringUtil.getBean("chatMsgServiceImpl");

        // 客户端发送过来的消息
        String content =  msg.text();
        System.out.println("接受的数据：" + content);
        Channel currentChannel = ctx.channel();

        // JsonUtils
        DataContent dataContent = JSON.parseObject(content, DataContent.class);
        WSChatMsg wsChatMsg = dataContent.getChatMsg();
        wsChatMsg.setNow(new Date());
        int action = dataContent.getAction();
        if ( MsgActionEnum.CONNECT.type == action ) {
            User user = null;
            Integer senderId =  wsChatMsg.getSenderID();
            if (null == senderId) {
                user = userService.getUserByNickName(wsChatMsg.getSenderNickname());
                if (null == user) {
                    user = new User();
                    user.setNickname(wsChatMsg.getSenderNickname());
                    userService.save(user);
                }
            } else {
                user = userService.getById(senderId);
            }
            wsChatMsg.setSenderID(user.getId());
            wsChatMsg.setSenderNickname(user.getNickname());
            UserChannel userChannel = new UserChannel(user, currentChannel);
            UserChannelRel.put(user.getId(), userChannel);


            TextWebSocketFrame sendMsh = new TextWebSocketFrame(JSON.toJSONString(dataContent));
            currentChannel.writeAndFlush(sendMsh);

            /**
             * 发送在线列表
             */
            sendOnlineList();

        } else if ( MsgActionEnum.CHAT.type == action ) {
            Integer receiverID = wsChatMsg.getReceiverID();
            if (null != receiverID && receiverID != 0) {
                ChatMsg chatMsg = new ChatMsg();
                chatMsg.setMsg(wsChatMsg.getMsg());
                chatMsg.setSendUserId(wsChatMsg.getSenderID());
                chatMsg.setAcceptUserId(wsChatMsg.getReceiverID());
                chatMsgService.save(chatMsg);
                UserChannel receiverChannel = UserChannelRel.get(receiverID);
                if (null != receiverChannel) {
                    Channel findChannel = clients.find(receiverChannel.getChannel().id());
                    if (null != findChannel) {
                        wsChatMsg.setMsgId(chatMsg.getId());
                        TextWebSocketFrame sendMsh = new TextWebSocketFrame(JSON.toJSONString(dataContent));
                        receiverChannel.getChannel().writeAndFlush(sendMsh);
                    }
                }
            } else {
                TextWebSocketFrame sendMsh = new TextWebSocketFrame(JSON.toJSONString(dataContent));
                clients.writeAndFlush(sendMsh);
            }
        } else if ( MsgActionEnum.OFFLINE.type == action ) {
            UserChannelRel.remove(wsChatMsg.getSenderID());

            /**
             * 发送在线列表
             */
            sendOnlineList();

        } else if ( MsgActionEnum.SIGNED.type == action ) {


        }

//        TextWebSocketFrame sendMsh = new TextWebSocketFrame(JSON.toJSONString(dataContent));
//        currentChannel.writeAndFlush(sendMsh);

        /**
            1. 获取客户端发来的消息
            2. 判断消息类型
                2.1 当websocket第一次open的时候，初始化channel，把用户的channel与userid关联起来
                2.2 聊天的消息，把聊天记录保存到数据库，标记消息状态
                2.3 签售消息类型，针对具体的消息进行签收，修改数据库中的对应消息的签收状态
                2.4 心跳类型
        **/




//        TextWebSocketFrame sendMsh = new TextWebSocketFrame(JSON.toJSONString(dataContent));
//        clients.writeAndFlush(sendMsh);

//        if (MsgActionEnum.CHAT.type == action) {
//            new TextWebSocketFrame(chatMsg.getSenderID() + ": " + chatMsg.getMsg());
//        }
//        for (Channel client : clients) {
//            client.writeAndFlush(textWebSocketFrame);
//        }

    }

    /**
     * 发送在线列表
     */
    private void sendOnlineList() {
        DataContent dc = new DataContent();
        dc.setOnlineList(UserChannelRel.MAP.values().stream().map(UserChannel::getUser).collect(Collectors.toList()));
        dc.setAction(MsgActionEnum.LINELIST.type);
        TextWebSocketFrame sendMsh = new TextWebSocketFrame(JSON.toJSONString(dc));
        clients.writeAndFlush(sendMsh);
    }


    /**
     * 第1步：助手类添加
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        /**
         * 获取客户端channel， 并放入ChannelGroup中进行管理
         */
        clients.add(ctx.channel());

    }


    /**
     * 第2步：注册 channel
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }


    /**
     * 第3步：活跃状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }


    /**
     * 第4步：读取数据完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }


    /**
     * 第5步：不活跃
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }


    /**
     * 第6步：移除 channel
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }


    /**
     * 第7步： 助手类移除
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 当触发handlerRemoved ChannelGroup会自动移除对应channel
        clients.remove(ctx.channel());

    }


    /**
     * 用户事件触发
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    /**
     * 可写被更改
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    /**
     * 捕获到异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        //  捕获到异常 关闭channel 移除client
        ctx.channel().close();
        clients.remove(ctx.channel());
    }


}
