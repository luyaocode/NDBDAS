package com.asurplus.common.onlyfortest;

/**
 * 静态方法不能直接调用非静态属性
 * 所有实例共享静态方法或者属性，所有实例拥有独立的非静态方法和属性
 */
public class TestStaticMethod {
    public static Integer id=1001;
    public String name="张三";
    public static Integer getId(){
        return id;
    }
    public static String getName(){
//        return name;
        return "";
    }
}
