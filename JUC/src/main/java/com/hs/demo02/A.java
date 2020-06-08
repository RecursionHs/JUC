package com.hs.demo02;

/**
 * @ClassName A
 * @Description synchronized加锁方式实现生产者消费者
 * @Author hsir
 * @Date 2020/6/8 下午10:21
 * @Version 1.0
 */
public class A {
    public static void main(String[] args) {
        ResourcesClass aClass = new ResourcesClass();
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
//判断等待、业务、唤醒
class ResourcesClass{
    private int number = 0;

    public synchronized void increment() throws InterruptedException{
        while (number != 0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + " +> " + number);
        this.notifyAll();
    }

    public synchronized void deIncrement() throws InterruptedException{
        while (number == 0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + " +> " + number);
        this.notifyAll();
    }
}