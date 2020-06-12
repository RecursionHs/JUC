package com.hs.signal;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;

/**
 * @ClassName TestCycliB
 * @Description 其实就是个加法
 * @Author hsir
 * @Date 2020/6/10 下午11:40
 * @Version 1.0
 */
public class TestCycliB {
    /**
     * 双色球产生
     * @param args
     */
    public static void main(String[] args) {
        int count = 7;
        List<Integer> array = new CopyOnWriteArrayList<>();
        CyclicBarrier barrier = new CyclicBarrier(count,()->{
            array.stream()
                    .sorted()
                    .forEach(System.out::println);

        });
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            int tmp = i;

            new Thread(()->{
                int tmpn = random.nextInt(33);
                System.out.println("用户" + tmp + "提供数字" + tmpn);
                try {
                    array.add(tmpn);
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }


    }
}
