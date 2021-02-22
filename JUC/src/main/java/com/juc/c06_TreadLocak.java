package com.juc;

import java.util.concurrent.TimeUnit;

/**
 * @Author:Controller
 * @date:2020-07-13 12:34 上午
 **/
public class c06_TreadLocak {
    static class Person {
        String name = "zhangsan";
    }

    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(tl.get());
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();
    }
}
