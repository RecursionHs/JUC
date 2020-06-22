package com.hs.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @ClassName DclSingle
 * @Description DCL懒汉式,反正任你花里胡哨，反射都能获取到对象
 * @Author hsir
 * @Date 2020/6/15 下午9:19
 * @Version 1.0
 */
public class DclSingle {

    private static boolean flag = false;

    private DclSingle(){
        synchronized (DclSingle.class){
            if(flag != true){
                throw new RuntimeException("不要通过反射获取对象实例！");
            }else{
                flag = false;
            }
        }
    }

    private static volatile DclSingle instance;

    public  static DclSingle getInstance(){
        if(instance == null){
            synchronized (DclSingle.class){
                if(instance == null){
                    flag = true;
                    instance =  new DclSingle();
                    }
            }

        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        DclSingle instance1 = DclSingle.getInstance();
        System.out.println(instance1);
        //DclSingle instance2 = DclSingle.getInstance();
        //System.out.println(instance1 == instance2);
        //通过反射新建对象 dclSingle == instance1  -> false
        //DclSingle dclSingle = DclSingle.class.newInstance();
        //System.out.println(dclSingle);
        //System.out.println(instance1 == dclSingle); //false
        //通过反射获取指定字段
        Field field = DclSingle.class.getDeclaredField("flag");
        field.setAccessible(true);
        field.set(null,true);
        Constructor<DclSingle> constructor = DclSingle.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        DclSingle dclSingle = constructor.newInstance();
        //DclSingle dclSingle2 = constructor.newInstance();
        System.out.println(dclSingle);
        //System.out.println(dclSingle == dclSingle2);
        //field.set(dclSingle,true);

    }
}

