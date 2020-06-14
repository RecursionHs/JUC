package com.hs.mvolatile;

/**
 * @ClassName Demo2
 * @Description 不保证原子性
 * @Author hsir
 * @Date 2020/6/14 下午10:38
 * @Version 1.0
 */
public class Demo2 {
    private volatile static int num = 0;

    /**
     * 反编译class文件，看到get这个方法其实是有几部的！不能够保证原子性!
     *   public static void get();
     *     Code:
     *        0: getstatic     #2                  // Field num:I
     *        3: iconst_1
     *        4: iadd
     *        5: putstatic     #2                  // Field num:I
     *        8: return
     */
    public static void get(){
        num++;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    get();
                }
            }).start();
            
        }
        //main gc
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
