package com.hs.blqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestBlQueue
 * @Description ArrayBlockingQueue本身就是FIFO结构，先进先出
 * 方式               抛出异常        有返回值，不抛出异常      阻塞 等待       超时等待
 * 添加               add            oﬀer()                put()          oﬀer(,,)
 * 移除               remove         poll()                take()         poll(,)
 * 检测队首元素        element        peek                   -              -
 * @Author hsir
 * @Date 2020/6/12 上午7:26
 * @Version 1.0
 */
public class TestBlQueue {
    public static void main(String[] args) throws Exception{
        //test1();
        //test2();
        //test3();
        test4();
    }

    /**
     * 抛出异常方式
     */
    public static void test1(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("A"));
        System.out.println(blockingQueue.add("B"));
        System.out.println(blockingQueue.add("C"));
        //IllegalStateException: Queue full
        //System.out.println(blockingQueue.add("D"));
        System.out.println("=======================");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //NoSuchElementException
        //System.out.println(blockingQueue.remove());
    }

    /**
     * 不抛异常的方式
     */
    public static void test2(){
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("A"));
        System.out.println(blockingQueue.offer("B"));
        System.out.println(blockingQueue.offer("C"));
        //不抛异常，返回false
        System.out.println(blockingQueue.offer("D"));
        System.out.println("============================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //不抛异常，返回Null
        System.out.println(blockingQueue.poll());
    }

    /**
     * 阻塞方式
     * @throws InterruptedException
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("A");
        blockingQueue.put("B");
        blockingQueue.put("C");
        System.out.println("添加abc结束...");
        //这里队列满了就会一直阻塞
        //blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //这里队列没值了，也会一直阻塞
        //System.out.println(blockingQueue.take());
    }

    /**
     * 超时阻塞
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.offer("A");
        blockingQueue.offer("B");
        blockingQueue.offer("C");
        System.out.println("ABC入队列完成！！");
        //这里等待2s，还入不到队列就放弃了
        blockingQueue.offer("D",2, TimeUnit.SECONDS);

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //这里等待2s，还取不到队列值就放弃了
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));

    }
}
