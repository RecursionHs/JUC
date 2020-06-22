package com.hs.demo02;

/**
 * @Author hsir
 * @Date 2020-6-17
 * @Version 1.0
 **/
public class D {

    public static void main(String[] args) {
        ResourceClass resourceClass = new ResourceClass();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    resourceClass.getNum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    resourceClass.removeNum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

    }
}

class ResourceClass{
    private volatile int num = 0;

    public synchronized void getNum() throws InterruptedException{
        while (num != 0 && num > 50){
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + " ->" +num);
        this.notify();
    }

    public synchronized void removeNum()throws InterruptedException{
        while(num == 0){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + " ->" +num);
    }

}
