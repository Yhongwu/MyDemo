package com.howard.interfaces;

/**
 * 接口的默认方法
 * 只能用public修饰，默认public修饰
 * Created by Howard Yao on 2018/6/4.
 */
public interface TestDefaultMethod {
    default void test() {
        System.out.println("这个是接口里的default方法test");
    }
    public default void test1() {
        System.out.println("这个是接口里的default方法test1");
    }
    //编译报错
//   private default void test2() {
//         System.out.println("这个是接口里的default方法");
//   }

    public static void main(String[] args) {
        //使用匿名内部类初始化实例
        TestDefaultMethod tx = new TestDefaultMethod() {};
        tx.test();
        tx.test1();
    }
}
