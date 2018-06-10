package com.howard.test.test03;

/**
 * 抽象类
 */
public abstract class A {
    /**
     * 抽象类中可以有成员变量，可以有常量
     */
    private int a;
    public int b;
    int c;
    String str;
    public static final String STR = "abstract";

    //抽象类中可以有构造方法
    public A() {

    }

    public void funA() {
        System.out.println("抽象类中可以有普通方法");
    }

    //抽象方法
    abstract void funB();
}
