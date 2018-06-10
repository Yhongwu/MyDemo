package com.howard.test.test03;

/**
 * Created by hongwu on 2018/3/18.
 * 抽象类不能被static修饰，但内部抽象类可以
 */
public abstract class F {

    abstract static  class G {
        abstract void funGA();

    }
    abstract void funA();

}

//实现内部抽象类
class H extends F.G {
    @Override
    void funGA() {
        System.out.println("实现F.G抽象类中的方法");
    }

    public static void main(String[] args) {
        F.G g = new H();
        g.funGA();
    }
}

