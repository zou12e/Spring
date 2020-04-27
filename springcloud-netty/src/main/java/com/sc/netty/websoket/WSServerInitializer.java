package com.sc.netty.websoket;

import com.sc.netty.handler.ChatHandler;
import com.sc.netty.handler.CustomHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 初始化器， channel注册后，会执行里面对应的初始化方法
 */
public class WSServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // SocketChannel 获取 pipeline
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 通过管道添加 handler
        // HttpServerCodec是由netty提供的助手类

        // 请求到服务端， 我们需要做解码， 响应客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());

        // 大数据流处理
        pipeline.addLast("ChunkedWriteHandler", new ChunkedWriteHandler());

        // HttpMessage聚合器， 聚合成 FullHttpRequest / FullHttpResponse
        pipeline.addLast("HttpObjectAggregator", new HttpObjectAggregator(1024 * 64));

        // ---------------      以上是用于支持http协议  --------------

        // websocket 服务器处理的协议， 用于指定给客户端连接的路由：/ws
        /**
         * 处理握手动作 close ping pong
         * websocket来讲 都是以frames来传输
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));


//        // 添加自定义助手类
//        pipeline.addLast("customHandler", new CustomHandler());

        pipeline.addLast("ChatHandler", new ChatHandler());

    }
}
