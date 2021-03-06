package com.juc.c03AtomicXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程之间的原子性、可见性可以用Atomic***类
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 * @Author:Controller
 * @date:2020-07-05 12:15 上午
 **/
public class T01_AtomicInteger {
    AtomicInteger count = new AtomicInteger(0);
    void m() {
        for (int i = 0; i < 10000; i++)
            count.incrementAndGet();
    }
    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();
        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }
        threads.forEach((o) -> o.start());
        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}
