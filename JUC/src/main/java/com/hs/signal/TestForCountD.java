package com.hs.signal;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestForCountD
 * @Description 等待排队一起过马路   CountDownLatch
 * @Author hsir
 * @Date 2020/6/10 下午11:29
 * @Version 1.0
 */
public class TestForCountD {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(5);
        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt(10) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                downLatch.countDown();
                System.out.println(Thread.currentThread().getName() + ":准备好了...");
            },String.valueOf(i)).start();
        }
        downLatch.await();
        System.out.println("一起过马路罗...");

    }
}
