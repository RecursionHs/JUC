package com.hs.threadPool;

import java.util.concurrent.*;

/**
 * @ClassName TestForThreadPool
 * 1.ThreadPoolExecutor.AbortPolicy()拒绝策略，当任务数大于最大可容纳任务(MaxNumberPoolSize + que size),会抛出如下异常.并终止程序
 * java.util.concurrent.RejectedExecutionException[Running, pool size = 5, active threads = 5, queued tasks = 3, completed tasks = 26]
 * 2.CallerRunsPolicy();这种拒绝策略,会将人物返回给调用者,比如这里就直接返回给Main线程,而不会抛出异常,只会多次使main线程执行业务逻辑
 * 3.DiscardPolicy();当任务数大于最大可容纳任务数(MaxNumberPoolSize + que size)时,直接就丢弃掉后面的任务了,不会执行了.
 * 4.DiscardOldestPolicy();这个跟上面那个差不多,只是会尝试与最早的线程竞争.
 * IO密集型与CPU密集型
 * CPU密集型可以根据自身CPU个数来比如Runtime.getRuntime().availableProcessors()获取
 * IO密集型，可以根据最大任务数来
 * @Description 正确的线程池的使用
 * @Author hsir
 * @Date 2020/6/13 上午11:35
 * @Version 1.0
 */
public class TestForThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2,  //核心线程数，最小活动数
                5, //可达的最大数
                0,  //超时后没人调用，就会释放线程
                TimeUnit.SECONDS, //时间单位
                new LinkedBlockingQueue<>(3), //任务队列长度
                Executors.defaultThreadFactory(),//线程工厂
                new ThreadPoolExecutor.DiscardPolicy()//拒绝策略
        );

        /**
         *最大能够容纳MaxNumberPoolSize + que size
         */
        try {
            for (int i = 0; i < 20; i++) {
                poolExecutor.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(6);
                        System.out.println(Thread.currentThread().getName() + " ok!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }

    }


}
