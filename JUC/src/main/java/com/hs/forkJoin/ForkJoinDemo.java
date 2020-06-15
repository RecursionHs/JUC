package com.hs.forkJoin;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName ForkJoinDemo
 * 求和计算的任务！* 30006000（ForkJoin）9000（Stream并行流）
 * 如何使用 forkjoin
 * 1、forkjoinPool 通过它来执行
 * 2、计算任务 forkjoinPool.execute(ForkJoinTask task)
 * 3. 计算类要继承 ForkJoinTask
 * @Author hsir
 * @Date 2020/6/14 下午9:12
 * @Version 1.0
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private Long startNum = 0L;
    private Long endNum = 0L;
    //临界值
    private Long criticalNum = 10000L;

    public ForkJoinDemo(Long startNum, Long endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    @Override
    protected Long compute() {
        Long retNum = 0L;
        if((endNum - startNum) < criticalNum){
            //小于临界值就走普通方法
            for (long i = startNum; i < endNum; i++) {
                retNum +=   i;
            }
            return retNum;
        }else{
            long middle = (startNum + endNum) / 2;
            // 中间值
            ForkJoinDemo task1 = new ForkJoinDemo(startNum, middle);
            task1.fork();
            // 拆分任务，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle+1, endNum);
            task2.fork();
            // 拆分任务，把任务压入线程队列
            return task1.join() + task2.join();
            }
    }
}
