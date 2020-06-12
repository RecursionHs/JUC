package com.hs.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadAndWriteLock
 * @Description 读写锁、ReentrantReadWriteLock
 *      独占锁(写锁) 一次只能被一个线程占有
 *      共享锁(读锁) 可以多个线程同时占有
 *      读-读 可以共存
 *      读-写 不可共存
 *      写-写 不可共存
 *      1.NoLockCache使用无锁的，写入数据顺序得不到控制，
 *      2.LockCache有了读写锁，写入数据操作具有原子性(开始写入->写出完毕)
 * @Author hsir
 * @Date 2020/6/12 上午6:58
 * @Version 1.0
 */
public class ReadAndWriteLock {
    public static void main(String[] args) {
        //NoLockCache cache = new NoLockCache();
        LockCache cache = new LockCache();

        //30个线程写
        for (int i = 1; i <= 30; i++) {
            final int temp = i;
            new Thread(()->{
                cache.put("" + temp,"" + temp);
            },"A"+i).start();
        }
        //30个线程读
        for (int i = 1; i <= 30; i++) {
            final int temp = i;
            new Thread(()->{
                cache.get("" + temp);
            },"B"+i).start();
        }

    }

}

/**
 * 自定义缓存
 */
class NoLockCache{
    private volatile Map<String,String> map = new HashMap<>();

    public void put(String key,String value){
        System.out.println(Thread.currentThread().getName() + "线程:开始写入key: " + key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() + "线程:写入key: " + key + "完毕！");
    }

    public String get(String key){
        System.out.println(Thread.currentThread().getName() + "线程:开始读出key: " + key);
        String s = map.get(key);
        System.out.println(Thread.currentThread().getName() + "线程:读出key: " + key + "完毕！");
        return s;
    }
}

class LockCache{
    private volatile Map<String,String>  map = new HashMap<>();
    //使用方法跟ReentrantLock一致，相同的模板代码,但是控制的更精细一些
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void put(String key,String value){
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "线程:开始写入key: " + key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "线程:写入key: " + key + "完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public String get(String key){
        rwLock.readLock().lock();
        String s = "";
        try {
            System.out.println(Thread.currentThread().getName() + "线程:开始读出key: " + key);
            s = map.get(key);
            System.out.println(Thread.currentThread().getName() + "线程:读出key: " + key + "完毕！");
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
        return s;
    }

}
