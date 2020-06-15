package com.hs.mvolatile;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName JMMDemo
 * @Description volatile的可见性
 * @Author hsir
 * @Date 2020/6/14 下午10:32
 * @Version 1.0
 */
public class JMMDemo {
    /**
     * 不加volatile就会一直循环
     * 加了后，变更值后就停止循环了
     */
    private volatile static int num = 0;

    public static void main(String[] args) {
        new Thread(()->{
            //如果值是0就一直循环
            while (num == 0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //修改值
        num = 1;
        System.out.println(num);
    }


}
