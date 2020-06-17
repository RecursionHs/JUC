package com.hs.spinLock;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestSpinLock
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/17 下午10:57
 * @Version 1.0
 */
public class TestSpinLock {

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo lockDemo = new SpinLockDemo();

        new Thread(()->{
            lockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockDemo.unLock();
            }
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            lockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockDemo.unLock();
            }
        },"B").start();
    }
}
