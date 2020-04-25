package com.sc.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 初始化器， channel注册后，会执行里面对应的初始化方法
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // SocketChannel 获取 pipeline
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 通过管道添加 handler
        // HttpServerCodec是由netty提供的助手类
        // 请求到服务端， 我们需要做解码， 响应客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());

        // 添加自定义助手类
        pipeline.addLast("customHandler", new CustomHandler());
    }
}
