package com.howard.test.test03;

/**
 * 接口
 */
public interface B {
    //接口里可以有常量。默认都是加了public final static
    //由于是常量，所以必须初始化
    //通过接口名.常量访问 如B.a
    int a = 2;
    public final static String str = "interface";

    //接口中的方法默认都是public abstract修饰
    void funA();

    public abstract void funB();

    static void funC() {
        System.out.println("jdk1.8新增的接口中的静态方法");
    }

    default void funD () {
        System.out.println("jdk1.8新增的接口中的默认方法实现");
    }

}
interface C extends B {
    int aa = 3;
}

interface D extends B,C {
    int dd = 4;
}
class E implements D {

    @Override
    public void funA() {
        System.out.println("实现接口A");
    }

    @Override
    public void funB() {
        System.out.println("实现接口B");
    }

    /*@Override
    public void funD() {
        System.out.println("覆盖默认实现");
    }*/

    public static void main(String[] args) {
        E e = new E();
        System.out.println("访问接口B中的常量...");
        //访问接口B中的常量
        System.out.println(B.a);
        System.out.println(E.a);
        System.out.println(C.a);

        //接口中的静态方法
        System.out.println("访问接口中的静态方法...");
        B.funC();

        System.out.println("访问类E中重写接口的方法...");
        e.funA();
        e.funB();

        System.out.println("访问接口中的默认方法...");
        e.funD();

    }
}