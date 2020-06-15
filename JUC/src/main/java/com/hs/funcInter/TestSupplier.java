package com.hs.funcInter;

import java.util.function.Supplier;

/**
 * @ClassName TestSupplier
 * @Description 4.Supplier 消费型接口，只有返回值，没有入参
 * @Author hsir
 * @Date 2020/6/13 下午11:26
 * @Version 1.0
 */
public class TestSupplier {
    public static void main(String[] args) {
        Supplier<String> supplier = new Supplier<String>(){
            @Override
            public String get() {
                return "hello!";
            }
        };
        System.out.println(supplier.get());
    }
}
