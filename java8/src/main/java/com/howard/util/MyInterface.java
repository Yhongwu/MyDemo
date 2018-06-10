package com.howard.util;

/**
 * 函数式接口
 * Created by Howard Yao on 2018/6/4.
 */
@FunctionalInterface
public interface MyInterface {
    //抽象方法
    int getValue(int num);

    //静态方法，不是抽象方法
    static void staticFunction() {
        System.out.println("接口里的静态方法！");
    }

    //存在一个抽象方法和静态方法的情况下，该接口仍然为函数式接口
}
