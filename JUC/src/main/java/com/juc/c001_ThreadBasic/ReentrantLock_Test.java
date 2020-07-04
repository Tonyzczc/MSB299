package com.juc.c001_ThreadBasic;

import java.util.concurrent.TimeUnit;

/**
 * 加锁方法可以调用非加锁方法
 * @Author:Controller
 * @date:2020-06-28 12:44 上午
 **/
public class ReentrantLock_Test {

    synchronized void m1() {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }
    public static void main(String[] args) {
        new ReentrantLock_Test().m1();
    }
}
