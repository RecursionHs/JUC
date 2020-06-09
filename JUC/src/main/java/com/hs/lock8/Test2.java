package com.hs.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test1
 * @Description  1.通过同一对象调用一个被synchronized修饰的方法和一个普通方法，两者互不影响。
 * 这里就会先打印hello -> 发短信
 * 2.两个不同的对象，调用同步方法,两者不影响，不是同一把锁，所以是 打电话 -> 发短信
 * @Author hsir
 * @Date 2020/6/9 下午10:27
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
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
 * synchronized锁的是方法的调用则，谁调用就锁谁
 */
class Phone2{

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
    //普通方法跟同步方法没关系，不受影响
    public  void hello(){
        System.out.println("hello");
    }
}
