package com.sc.netty.websoket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class WSServer {

    private static class SingletionWSServer {
        static  final  WSServer instance = new WSServer();
    }

    public static WSServer getInstance() {
        return SingletionWSServer.instance;
    }

    private  EventLoopGroup mainGroup;
    private  EventLoopGroup subGroup;
    private  ServerBootstrap serverBootstrap;
    private  ChannelFuture channelFuture;

    public WSServer() {
        // 定义一对线程组
        // 主线程租 接受客户端连接，不做任何处理
        mainGroup = new NioEventLoopGroup();
        // 从线程组 主线程组把任务分给从线程组
        subGroup = new NioEventLoopGroup();

        // netty服务器创建
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainGroup, subGroup) // 设置主从线程组
                .channel(NioServerSocketChannel.class) // 设置nio的双向通道
                .childHandler(new WSServerInitializer());  // 子处理器，从线程组做拦截处理
    }

    public void start() {
        this.channelFuture =  serverBootstrap.bind(7070);

    }



//    public static void main(String[] args) throws InterruptedException {
//        // client - bossGroup - workerGroup - channel - pipeline(多个handler)
//
//        // 定义一对线程组
//        // 主线程租 接受客户端连接，不做任何处理
//        EventLoopGroup mainGroup = new NioEventLoopGroup();
//        // 从线程组 主线程组把任务分给从线程组
//        EventLoopGroup subGroup = new NioEventLoopGroup();
//
//        try {
//            // netty服务器创建
//            ServerBootstrap serverBootstrap = new ServerBootstrap();
//            serverBootstrap.group(mainGroup, subGroup) // 设置主从线程组
//                            .channel(NioServerSocketChannel.class) // 设置nio的双向通道
//                            .childHandler(new WSServerInitializer());  // 子处理器，从线程组做拦截处理
//
//            // 启动server 启动方式设置同步方式
//            ChannelFuture channelFuture = serverBootstrap.bind(7070).sync();
//            // 监听关闭channel 设置同步方式
//            channelFuture.channel().closeFuture().sync();
//        } finally {
//            mainGroup.shutdownGracefully();
//            subGroup.shutdownGracefully();
//        }
//    }
}
