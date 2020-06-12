package com.hs.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test1
 * @Description  1.通过同一对象调用两个被static修饰的同步方法， 发短信->打电话，这里就锁的是phone3的class,而class
 * 一个类都只有一个，所以这里会等待A执行完，再执行B
 * 2.就算是用两个不同的对象但是是同一个class，所以还是会锁class,  发短信 -> 打电话
 * @Author hsir
 * @Date 2020/6/9 下午10:27
 * @Version 1.0
 */
public class Test3 {
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();
        new Thread(()->{
            phone1.sendMessage();
        },"A").start();

        try {
            //这里的sleep为了避免CPU时间片问题，有同步情况下，上面的先得到锁
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.callPhone();
        },"B").start();
    }

}

/**
 * 类一加载，class就加锁了
 */
class Phone3{

    public static synchronized void sendMessage(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void callPhone(){
        System.out.println("打电话");
    }

    }
