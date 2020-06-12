package com.hs.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test1
 * @Description 1.通过同一对象调用都被synchronized修饰的方法后，锁的是当前对象，线程A获取锁后
 * 执行完sendMessage方法后，B拿到锁就执行B的方法。 发短信->打电话
 * 2.在第一个方法sleep 4s后现象就明显了  B线程会等待A执行完毕后，拿到锁再执行 发短信->打电话
 * @Author hsir
 * @Date 2020/6/9 下午10:27
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendMessage();
        },"A").start();

        try {
            //这里的sleep为了避免CPU时间片问题，有同步情况下，上面的先得到锁
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.callPhone();
        },"B").start();
    }

}

class Phone{

    public synchronized void sendMessage(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void callPhone(){
        System.out.println("打电话");
    }

}
