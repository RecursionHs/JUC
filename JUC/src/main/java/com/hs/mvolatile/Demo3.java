package com.hs.mvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Demo2
 * @Description 不保证原子性
 * @Author hsir
 * @Date 2020/6/14 下午10:38
 * @Version 1.0
 */
public class Demo3 {
    //private volatile static int num = 0;

    /**
     * 使用atomic原子类处理相关原子性问题
     */
    private static AtomicInteger num = new AtomicInteger();


    public static void get(){
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    get();
                }
            }).start();
            
        }
        //main gc
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
