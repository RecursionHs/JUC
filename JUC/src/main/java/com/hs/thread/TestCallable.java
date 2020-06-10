package com.hs.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestCallable
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/10 下午10:58
 * @Version 1.0
 */
public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask();

        //适配类
        FutureTask futureTask = new FutureTask(myTask);
        //多个线程只会执行一个futureTask，这个问题后面再研究
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        //这个方法会阻塞！
        String retStr = (String)futureTask.get();
        System.out.println(retStr);

    }


}

/**
 * 类的范型类型就为返回值的类型
 */
class MyTask implements Callable<String>{

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        System.out.println(new Random().nextInt(10));
        System.out.println("callable task...");
        return "成功!";
    }
}