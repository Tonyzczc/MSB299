package com.juc.c03AtomicXXX;

import java.util.concurrent.atomic.LongAdder;

/**
 * @Author:Controller
 * @date:2020-07-05 12:18 上午
 **/
public class T02_LongAdder {

    static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for (int t = 0; t < threads.length; t++) {
            threads[t] = new Thread(() -> {
                for (int k = 0; k < 10000; k++) count.increment();
            });
        }
        long start = System.currentTimeMillis();

        for(Thread t : threads ) t.start();

        for (Thread t : threads) t.join();

        long end = System.currentTimeMillis();

        System.out.println("LongAdder: " + count.longValue() + " time " + (end-start));
    }
}
