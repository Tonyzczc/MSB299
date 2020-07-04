package com.juc.c04_Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 是可以替换 synchronized
 * 不过使用synchronized锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 * 使用reentrantlock可以进行“尝试锁定”tryLock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 * @Author:Controller
 * @date:2020-07-05 12:34 上午
 **/
public class T01_ReentrantLock {
    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();
            for (int i=0 ; i<10 ;i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2(){
        try {
            lock.lock();
            System.out.println("m2.....");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T01_ReentrantLock t = new T01_ReentrantLock();
        new Thread(t::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2).start();
    }
}
