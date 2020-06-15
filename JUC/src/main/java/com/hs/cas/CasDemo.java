package com.hs.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CasDemo
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/15 下午10:29
 * @Version 1.0
 */
public class CasDemo {
    // CAScompareAndSet : 比较并交换！
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2000);

        // 期望、更新
        // public final boolean compareAndSet(int expect, int update)
        // 如果我期望的值达到了，那么就更新，否则，就不更新, CAS 是CPU的并发原语！
        atomicInteger.compareAndSet(2000,2001);
        System.out.println(atomicInteger.get());

        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(2002,2003));
        System.out.println(atomicInteger.getAndIncrement());
    }
}
