package com.hs.signal;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SemaphoreTest
 * @Description 信号量，限流作用
 * 从输出可以明显看出最多只有5个人同时吃饭
 * semaphore.acquire()获得，假设如果已经满了，等待，等待被释放为止！
 * semaphore.release();释放，会将当前的信号量释放 + 1，然后唤醒等待的线程！
 * 作用： 多个共享资源互斥的使用！并发限流，控制最大的线程数！
 * @Author hsir
 * @Date 2020/6/12 上午6:47
 * @Version 1.0
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        //饭店只有5个坐位能够吃饭
        Semaphore semaphore = new Semaphore(5);
        Random random = new Random();
        //20个人排队吃饭
        for (int i = 1; i <= 20; i++) {
            int tmp = i;
            int anInt = random.nextInt(11);
            new Thread(()->{
                try {
                    //得到锁，开始行动
                    semaphore.acquire();
                    System.out.println("用户" + tmp + ":进入餐馆开始吃饭...");
                    //这里可以看作具体的业务逻辑
                    TimeUnit.SECONDS.sleep(anInt);
                    System.out.println("用户" + tmp + ":吃了" + anInt + "秒，离开饭店了...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
