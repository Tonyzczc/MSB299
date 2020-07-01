package com.study.netty.hello;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


/**
 * @Author:Controller
 * @date:2020-07-02 12:03 上午  创建自定义助手类
 **/
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        Channel channel = ctx.channel();

        if(msg instanceof HttpRequest){
            // 显示客户端的远程地址
            System.out.println(channel.remoteAddress());

            ByteBuf content = Unpooled.copiedBuffer("Hello~~~", CharsetUtil.UTF_8);
            FullHttpResponse response =
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                            HttpResponseStatus.OK,
                            content);
            // 为响应增加数据类型和长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 把响应刷到客户端
            ctx.writeAndFlush(response);
        }
    }
}
