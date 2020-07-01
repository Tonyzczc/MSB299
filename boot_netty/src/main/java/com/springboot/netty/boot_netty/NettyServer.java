package com.springboot.netty.boot_netty;

import org.springframework.stereotype.Component;

/**
 * @Author:Controller
 * @date:2020-07-01 11:52 下午
 **/
@Component
public class NettyServer {
    private static class SingletionNettyServer{
        static final NettyServer instance = new NettyServer();
    }
    public static NettyServer getInstance(){
        return SingletionNettyServer.instance;
    }














}
