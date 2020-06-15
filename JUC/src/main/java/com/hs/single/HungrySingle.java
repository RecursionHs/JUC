package com.hs.single;

/**
 * @ClassName HungrySingle
 * @Description 最简单的恶汉式单利模式
 * @Author hsir
 * @Date 2020/6/15 下午9:17
 * @Version 1.0
 */
public class HungrySingle {

    private HungrySingle(){

    }

    private static HungrySingle instance = new HungrySingle();

    public HungrySingle getInstance(){
        return instance;
    }
}
