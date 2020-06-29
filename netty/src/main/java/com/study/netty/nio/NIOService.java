package com.study.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author:Controller
 * @date:2020-06-29 10:06 下午
 **/
public class NIOService {
    public static void main(String[] args) throws Exception {
        // 1: 创建网络服务端 ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        // 2：selector 注册到channel
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector,0,serverSocketChannel); //每个selector 注册到 channel 会产生一个selectionKey，共有两种操作：interest ready
        selectionKey.interestOps(SelectionKey.OP_ACCEPT); // interest 操作：OP_CONNECT:连接操作,Client端支持的一种操作

        // 3:绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        while(true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                //关注read 和 accept   accept：注册时间 read:发送
                if(key.isAcceptable()){
                    ServerSocketChannel attachChannel = (ServerSocketChannel) key.attachment();
                    SocketChannel clientSocketChannel = attachChannel.accept();
                    clientSocketChannel.configureBlocking(false);
                    clientSocketChannel.register(selector,SelectionKey.OP_READ,clientSocketChannel);
                    System.out.println("收到新连接 : " + clientSocketChannel.getRemoteAddress());
                }

                if(key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.attachment();
                    try {
                        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                        while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
                            // 长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
                            if (requestBuffer.position() > 0) break;
                        }
                        if(requestBuffer.position() == 0) continue; // 如果没数据了, 则不继续后面的处理
                        requestBuffer.flip();
                        byte[] content = new byte[requestBuffer.limit()];
                        requestBuffer.get(content);
                        System.out.println(new String(content));
                        System.out.println("收到数据,来自：" + socketChannel.getRemoteAddress());

                        // 响应结果 200
                        String response = "HTTP/1.1 200 OK\r\n" +
                                "Content-Length: 11\r\n\r\n" +
                                "Hello World";
                        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                        while (buffer.hasRemaining()) {
                            socketChannel.write(buffer);
                        }
                    } catch (IOException e) {
                        // e.printStackTrace();
                        key.cancel(); // 取消事件订阅
                    }


                }
            }
        }








    }
}
