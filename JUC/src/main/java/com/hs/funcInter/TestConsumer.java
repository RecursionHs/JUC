package com.hs.funcInter;

import java.util.function.Consumer;

/**
 * @ClassName TestConsumer
 * @Description 3.Consumer 只能接受参数，可以用来处理业务逻辑，没有返回值
 * @Author hsir
 * @Date 2020/6/13 下午11:22
 * @Version 1.0
 */
public class TestConsumer {
    public static void main(String[] args) {
        /**
         * public interface Consumer<T> {
         *     void accept(T var1);
         */
        /*Consumer<String> consumer = new Consumer<String>(){
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };*/
        Consumer<String> consumer = (s) ->{
            System.out.println(s);
        };

        consumer.accept("hello");
    }
}
