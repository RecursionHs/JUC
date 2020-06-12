package com.hs.demo02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName C
 * @Description LOCK加锁方式实现生产者消费者;按照ABC顺序
 * @Author hsir
 * @Date 2020/6/8 下午10:21
 * @Version 1.0
 */
public class C {
    public static void main(String[] args) {
        ResourcesClass3 aClass = new ResourcesClass3();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aClass.print1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aClass.print2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aClass.print3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }

}

//判断等待、业务、唤醒

/**
 * A -> 1,B -> 2,C -> 3
 */
class ResourcesClass3{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    //condition.await(); --> wait
    //condition.signalAll(); --> notifyAll();


    public  void print1() throws InterruptedException{
        lock.lock();
        try {
            while (number != 1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + " +> AAA " + number);
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public  void print2() throws InterruptedException{
        lock.lock();
        try {
            while (number != 2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + " +> BBB " + number);
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public  void print3() throws InterruptedException{
        lock.lock();
        try {
            while (number != 3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + " +> CCC " + number);
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}