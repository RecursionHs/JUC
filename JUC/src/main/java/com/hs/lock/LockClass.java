package com.hs.lock;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockClass
 * @Description 锁的可重入性
 * 拿到一把锁后，就能拿到里面的锁，所以看到的现象是一个线程执行完所有的方法后，其它线程才有机会拿锁执行同步方法
 * @Author hsir
 * @Date 2020/6/17 下午8:45
 * @Version 1.0
 */
public class LockClass {

    public static void main(String[] args) {
        //BuyFood buyFood = new BuyFood();
        BuyFoodByLock buyFood = new BuyFoodByLock();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                buyFood.buyRoastDuck();
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                buyFood.buyRoastDuck();
            }
            },"B").start();
    }
}

class BuyFood{

    public  synchronized void buyRoastDuck(){
        System.out.println(Thread.currentThread().getName() + "-> buy RoastDuck!");
        buyFriednoodles();
    }

    private  synchronized void buyFriednoodles() {
        System.out.println(Thread.currentThread().getName() + "-> buy Friednoodles!");
    }

}

/**
 * 锁要对应解锁
 */
class BuyFoodByLock{
    private ReentrantLock lock = new ReentrantLock();

    public   void buyRoastDuck(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "-> buy RoastDuck!");
            buyFriednoodles();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    private   void buyFriednoodles() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "-> buy Friednoodles!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
