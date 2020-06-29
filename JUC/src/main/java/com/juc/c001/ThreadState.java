package com.juc.c001;

/**
 * 创建线程池有三种方式 Thread Runnable Executors.newCachedThread
 * @Author:Controller
 * @date:2020-06-27 11:42 下午
 **/
public class ThreadState {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Run");
        }
    }

    static class MyRunnable implements Runnable{

        public void run() {
            System.out.println("Runnable");
        }
    }


    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
        new Thread(()->{
            System.out.println("Hello Lambda!");
        }).start();

        Thread t = new Thread();

    }
}
