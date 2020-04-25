package com.sc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcloudNettyApplicationTests {

    @Test
    public void contextLoads() throws Exception {

        // 定义一对线程组
        // 主线程租 接受客户端连接，不做任何处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        // 从线程组 主线程组把任务分给从线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // client - bossGroup - workerGroup - channel - pipeline(多个handler)
        try {
            // netty服务器创建
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup) // 设置主从线程组
                    .channel(NioServerSocketChannel.class) // 设置nio的双向通道
                    .childHandler(new ServerInitializer()); // 子处理器，从线程组做拦截处理

            // 启动server 启动方式设置同步方式
            ChannelFuture channelFuture = serverBootstrap.bind(7070).sync();

            // 监听关闭channel 设置同步方式
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
