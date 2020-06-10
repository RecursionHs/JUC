package com.hs.conllection;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName TestMap
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/10 下午10:54
 * @Version 1.0
 */
public class TestMap {

    public static void main(String[] args) {
        //Map<String, String> hashMap = new HashMap<>(); ConcurrentModificationException
        Map<String, String> hashMap = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                hashMap.put(Thread.currentThread().getName(),
                        UUID.randomUUID().toString().substring(0,5));
                System.out.println(hashMap);
            },String.valueOf(i)).start();
        }
    }
}
