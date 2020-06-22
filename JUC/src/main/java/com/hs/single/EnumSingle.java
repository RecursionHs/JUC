package com.hs.single;

/**
 * @ClassName EnumSingle
 * @Description 枚举类获取对象实例
 * @Author hsir
 * @Date 2020/6/15 下午10:04
 * @Version 1.0
 */
public enum  EnumSingle {

    INSTANCE;

    public EnumSingle getInstance(){
        return INSTANCE;
    }
}
class Test{
    public static void main(String[] args) {
        EnumSingle instance = EnumSingle.INSTANCE;
        System.out.println(instance);
    }
}
