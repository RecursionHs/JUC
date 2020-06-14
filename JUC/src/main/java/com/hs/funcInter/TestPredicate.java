package com.hs.funcInter;

import java.util.function.Predicate;

/**
 * @ClassName TestPredicate
 * @Description 2.Predicate  只有一个入参，返回值固定为boolean
 * @Author hsir
 * @Date 2020/6/13 下午11:17
 * @Version 1.0
 */
public class TestPredicate {
    public static void main(String[] args) {
        /**
         * public interface Predicate<T> {
         *     boolean test(T var1);
         */
   /*     Predicate<String> predicate = new Predicate<String>(){
            @Override
            public boolean test(String o) {
                return o.isEmpty();
            }
        };*/
        Predicate<String> predicate = (s) ->{ return s.isEmpty();};

        System.out.println(predicate.test(""));
    }
}
