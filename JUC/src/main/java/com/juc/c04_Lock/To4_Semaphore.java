package com.juc.c04_Lock;

import java.util.concurrent.Semaphore;

/**
 * @Author:Controller
 * @date:2020-07-06 12:22 上午
 **/
public class To4_Semaphore {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(2, true);
        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");

                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
