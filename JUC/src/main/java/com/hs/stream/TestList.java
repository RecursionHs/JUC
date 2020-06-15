package com.hs.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestList
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/13 下午11:28
 * @Version 1.0
 */
public class TestList {
    /*** 题目要求：一分钟内完成此题，只能用一行代码实现！* 现在有5个用户！筛选：*
     * 1、ID 必须是偶数
     * 2、年龄必须大于23岁
     * 3、用户名转为大写字母
     * 4、用户名字母倒着排序
     * 5、只输出一个用户！
     * */
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(211,"jerry",24,"成都"));
        list.add(new Person(212,"heler",25,"浙江"));
        list.add(new Person(213,"cidy",26,"泸州"));
        list.add(new Person(214,"fish",27,"绵阳"));
        list.add(new Person(215,"tom",28,"绵竹"));
        list.add(new Person(217,"ninda",29,"凯里"));
        list.add(new Person(218,"fenaro",32,"广西"));

        list.stream()
                .filter((p) -> {return p.getId() % 2 == 0;})
                .filter((p)->p.getAge() > 23)
                .map((p) -> {return p.getName().toUpperCase();})
                .sorted((n1,n2) -> {return n2.compareTo(n1);})
                .limit(1)
                .forEach(System.out::println);
    }
}
