package com.howard.interfaces;

/**
 * Created by Howard Yao on 2018/6/4.
 */
public interface A {
    default void funcA(){
        System.out.println("接口A中的默认方法");
    }

    public static void main(String[] args) {
        D d = new D();
        d.funcA();
    }
}
interface B {
    default void funcA(){
        System.out.println("接口B中的默认方法");
    }
}

class E implements A,B {

    //必须重写
    @Override
    public void funcA() {

    }
}

class C implements A {
    @Override
    public void funcA() {
        System.out.println("重写funcA");
    }
}

class D extends C implements A {

}


