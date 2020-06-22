package com.hs.demo02;

import java.util.concurrent.TimeUnit;

/**
 * @Author hsir
 * @Date 2020-6-17
 * @Version 1.0
 **/
public class TestWait {

    public static void main(String[] args) {
        CWait cWait = new CWait();
        cWait.get();

    }

}
class CWait{

    public synchronized void get(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this);
    }
}
