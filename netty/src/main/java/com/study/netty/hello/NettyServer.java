package com.study.netty.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author:Controller
 * @date:2020-07-01 11:56 下午
 **/
public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup boosGroup = new NioEventLoopGroup(); //主线程
        EventLoopGroup workererGroup = new NioEventLoopGroup(); // 工作线程

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boosGroup,workererGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new HelloServerInitializer());


        try {
            ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            workererGroup.shutdownGracefully();
        }


    }
}
