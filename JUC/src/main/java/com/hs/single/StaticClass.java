package com.hs.single;

/**
 * @ClassName StaticClass
 * @Description 静态内部类
 * @Author hsir
 * @Date 2020/6/15 下午10:02
 * @Version 1.0
 */
public class StaticClass {
    private StaticClass(){}

    public static StaticClass getInstance(){
        return InnerClass.instance;
    }

    public static class InnerClass{
        private static final StaticClass instance = new StaticClass();
    }
}
