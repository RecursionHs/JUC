package com.hs.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TestThreadPool
 * @Description
 * @Author hsir
 * @Date 2020/6/12 下午10:49
 * @Version 1.0
 */
public class TestThreadPool {
    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//但线程!
        //ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩的，看实际情况增加线程数
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//固定大小线程！
        try {
            for (int i = 1; i <= 100; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完就关掉
            threadPool.shutdown();
        }

    }

}
