package com.hs.funcInter;

import java.util.function.Function;

/**
 * @ClassName TestFunction
 * @Description 四大函数式接口 1.Function      有输入及输出参数
 * @Author hsir
 * @Date 2020/6/13 下午11:10
 * @Version 1.0
 */
public class TestFunction {
    public static void main(String[] args) {
        /**
         * public interface Function<T, R> {
         *     R apply(T var1);
         *     T：入参  R：返回值
         */
       /* Function<String, String> function = new Function<String,String>(){
            @Override
            public String apply(String s) {
                return s + "!";
            }
        };*/

        //lamda方式：
        Function<String,String> function = (t) -> {return t + "!";};
        System.out.println(function.apply("hello"));
    }
}
