package com.hs.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestDemo
 * @Description 异步执行，类似ajax
 * @Author hsir
 * @Date 2020/6/14 下午10:13
 * @Version 1.0
 */
public class TestDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //没有返回值的异步回调
        /*CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync=>void");
        });
        System.out.println("1");
        // 获取阻塞执行结果
        future.get();
        System.out.println("2");*/

        //有返回值的异步
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            int i = 10/0;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1024;
        });

        System.out.println("3");
        supplyAsync.whenComplete((i,e)->{
            //i ->null
            System.out.println("i ->" + i);
            //e ->java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
            System.out.println("e ->" + e);
        }).exceptionally((e)->{
            //java.lang.ArithmeticException: / by zero
            System.out.println(e.getMessage());
            return 28;
        }).get();

        System.out.println("4");
    }
}
