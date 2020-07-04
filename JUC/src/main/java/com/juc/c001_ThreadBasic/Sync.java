package com.juc.c001_ThreadBasic;

/**
 * 重点：
 * 1：锁的是对象不是代码
 * 2：this xxx.class
 * 3：锁定方法、非锁定方法可以同时执行
 * 4：锁升级  synchronize锁升级过程：偏向锁 --> 自旋锁 --> 重量级锁
 * 偏向锁（对象头记录线程号，先不进行加锁）  自旋锁（等待--转圈）  重量级（等待队列，不占用CPU，内存等系统资源）
 * 执行时间短（加锁代码），线程数少，用自旋
 * 执行时间长，线程数多，用系统锁
 * * synchronize关键字不要修饰字符串
 * * 对对象加锁，改变内部属性可以，但是更改对象指定对象会影响锁
 * @Author:Controller
 * @date:2020-06-27 11:43 下午
 **/
public class Sync {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 ");
    }


    public static void main(String[] args) {
        Sync t = new Sync();
        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();
    }
}
