package com.study.netty.nio;

import java.nio.ByteBuffer;

/**
 * @Author:Controller
 * @date:2020-06-29 9:43 下午
 * Buffer : 数据从通道读入缓冲区，从缓冲区写入通道
 **/
public class BufferDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4);
        System.out.println(String.format("初始化：%s---position位置:%s-------limit限制:%s---",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit()));
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        System.out.println(String.format("初始化：%s---position位置:%s-------limit限制:%s---",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit()));
        byteBuffer.flip();//作为读取位置
        System.out.println(byteBuffer.get());
        System.out.println(String.format("初始化：%s---position位置:%s-------limit限制:%s---",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit()));
        byteBuffer.compact(); // buffer : 2
        byteBuffer.put((byte) 3);
        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 5);
        System.out.println(String.format("最终的情况，capacity容量：%s, position位置：%s, limit限制：%s", byteBuffer.capacity(),
                byteBuffer.position(), byteBuffer.limit()));
        // rewind() 重置position为0
        // mark() 标记position的位置
        // reset() 重置position为上次mark()标记的位置
    }
}
