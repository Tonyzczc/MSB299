package com.juc.c02Volatile;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字使一个变量在多个线程中保持可见
 * A B线程都有使用一个变量，都先拷贝到线程自身，然后在使用，线程更改后其他线程并不知道，
 * 使用volatile关键字修饰后各个线程修改变得可见，只能保证引用本身可见，并不能保证内部数据可见
 * @Author:Controller
 * @date:2020-07-04 11:59 下午
 **/
public class T01_HelloVolatile {

    volatile boolean running = true;

    void m1(){
        System.out.println("m1---start");
        while(running){

        }
        System.out.println("m1---end");
    }
    public static void main(String[] args) {
        T01_HelloVolatile t1 = new T01_HelloVolatile();
        new Thread(t1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.running=false;
    }
}
