package com.hs.demo02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName SaleTicketDemo01
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/8 下午9:44
 * @Version 1.0
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        new Thread(() -> {for (int i = 0; i < 60; i++) {
            ticket.saleT();
        }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.saleT();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.saleT();
            }
        },"C").start();
    }

}

class Ticket2{
    private  int countNumber = 50;
    Lock lock = new ReentrantLock();
    public  void saleT(){
        lock.lock();
        try {
            if(countNumber > 0){
                System.out.println(Thread.currentThread().getName()
                        + " 卖出了:" + countNumber-- + "剩余:" + countNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
