package com.hs.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @ClassName TestDemo
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/14 下午9:03
 * @Version 1.0
 */
public class TestDemo {
    private static Long startNum = 1L;
    private static Long endNum = 10_0000_0000L;
    //临界值
    private static Long criticalNum = 10000L;

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        //test1();//499999999500000000    4553
        test2();//499934463999828224    3785
        //test3();//499999999500000000    677
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    public static void test1(){
        Long retNum = 0L;
        for (long i = startNum; i < endNum; i++) {
            retNum +=i;
        }
        System.out.println("计算结果：" + retNum);
    }

    public static void test2(){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo joinDemo = new ForkJoinDemo(startNum, endNum);
        ForkJoinTask<Long> joinTask = forkJoinPool.submit(joinDemo);
        try {
            Long retNum = joinTask.get();
            System.out.println("计算结果：" + retNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void test3(){
        Long retNum = 0L;

        retNum = LongStream.range(startNum,endNum).parallel().reduce(0,Long::sum);

        System.out.println("计算结果：" + retNum);
    }

}
