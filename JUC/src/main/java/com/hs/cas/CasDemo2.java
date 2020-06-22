package com.hs.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName CasDemo2
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/16 下午9:40
 * @Version 1.0
 */
public class CasDemo2 {
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

    public static void main(String[] args) {
        new Thread(()->{
            //版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println("A1 ->" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(1,2,atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);

            System.out.println("A2 -> " + atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("A3 -> " + atomicStampedReference.getStamp());

        },"A").start();

        //乐观锁
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("B1 -> " + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 6, stamp, stamp + 1));
            System.out.println("B2 -> " + atomicStampedReference.getStamp());
        },"B").start();
    }
}
