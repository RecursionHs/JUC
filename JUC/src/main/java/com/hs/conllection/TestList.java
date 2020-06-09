package com.hs.conllection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName TestList
 * @Description list在多线程下的安全性
 * @Author hsir
 * @Date 2020/6/9 下午11:14
 * @Version 1.0
 */
public class TestList {

    public static void main(String[] args) {
        //
        /**
         * 1.arraylist多线程下的异常Exception in thread "3" Exception in thread "6"
         * java.util.ConcurrentModificationException
         * 2.Vector从1.0版本，而arraylist是1.2,虽然是线程安全的，但是不是专业的
         * 3.List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 4.List<String> list = new CopyOnWriteArrayList<>();
         * CopyOnWrite 写入时复制，多线程写入数据时，先复制一份，操作复制的对象，在插入到原来的对象里面，避免
         * 多线程操作同一个对象造成写入数据覆盖等现象！
         */
        //List<String> list = new ArrayList<>();

        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();



        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
