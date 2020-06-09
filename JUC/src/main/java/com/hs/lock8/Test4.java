package com.hs.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test1
 * @Description  1.1个静态的同步方法，1个普通的同步方法 ，一个对象，还是两个不同的锁，所以 打电话 -> 发短信
 * 2.1个静态的同步方法，1个普通的同步方法 ，两个对象，还是不同的锁    打电话 -> 发短信
 * @Author hsir
 * @Date 2020/6/9 下午10:27
 * @Version 1.0
 */
public class Test4 {
    public static void main(String[] args) {
        // 两个对象的Class类模板只有一个，static，锁的是Class
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
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

class Phone4{

    public static synchronized void sendMessage(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public  synchronized void callPhone(){
        System.out.println("打电话");
    }


}
