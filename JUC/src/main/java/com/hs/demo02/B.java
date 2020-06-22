package com.hs.demo02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName A
 * @Description LOCK加锁方式实现生产者消费者
 * @Author hsir
 * @Date 2020/6/8 下午10:21
 * @Version 1.0
 */
public class B {
    public static void main(String[] args) {
        ResourcesClass2 aClass = new ResourcesClass2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aClass.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aClass.deIncrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aClass.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aClass.deIncrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }

}

/**
 *     默认非公平锁
 *     public ReentrantLock() {
 *         this.sync = new ReentrantLock.NonfairSync();
 *     }
 *
 *     public ReentrantLock(boolean var1) {
 *         this.sync = (ReentrantLock.Sync)(var1 ? new ReentrantLock.FairSync() : new ReentrantLock.NonfairSync());
 *     }
 */
//判断等待、业务、唤醒
class ResourcesClass2{
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    //condition.await(); --> wait
    //condition.signalAll(); --> notifyAll();

    public  void increment() throws InterruptedException{
        lock.lock();
        try {
            while (number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + " +> " + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public  void deIncrement() throws InterruptedException{
        lock.lock();
        try {
            while (number == 0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + " +> " + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}