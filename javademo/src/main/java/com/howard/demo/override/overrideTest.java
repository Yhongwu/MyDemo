package com.howard.demo.override;
/**
 * 测试重写
 * B类继承了A并重写了方法。测试后可发现实现了多态
 * @author Howard
 * 2017年2月9日
 */
public class overrideTest {
	public static void main(String[] args) {
		A test01 = new A();
		A test02 = new B();
		test01.print();
		test02.print();
	}
}
class A{
	public void print(){
		System.out.println("A 类的方法");
	}
}

class B extends A{
	//重写
	public void print(){
		System.out.println("B(继承了A) 类的方法");
	}
}