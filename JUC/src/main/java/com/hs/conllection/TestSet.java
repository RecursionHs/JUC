package com.hs.conllection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName TestSet
 * @Description HashSet底层就是利用了hashMap的key的唯一性，而value都是new Object
 * @Author hsir
 * @Date 2020/6/10 下午10:44
 * @Version 1.0
 */
public class TestSet {

    public static void main(String[] args) {
        //Set<String> set = new HashSet<>(); ConcurrentModificationException
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet();

        for (int i = 1; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }

    }
}
