package com.hs.spinLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName SpinLockDemo
 * @Description 自旋机制锁
 * @Author hsir
 * @Date 2020/6/17 下午10:51
 * @Version 1.0
 */
public class SpinLockDemo {
    //Thread null
    private AtomicReference<Thread> oar = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "-> myLock");
        //自旋
        while(!oar.compareAndSet(null,thread)){

        }
    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "-> unLock");
        oar.compareAndSet(thread,null);
    }
}
