package com.howard.demo.clsorinter;
/**
 * 抽象类
 * @author Howard
 * 2017年2月17日
 */
public abstract class TestAbstract {

	public int a;
	private int b;
	int c;
	public static int d;
	private static int e;
	
	public TestAbstract(){
		System.out.println("构造方法1");
	}
	public TestAbstract(int a){
		System.out.println("构造方法2");
	}
	//普通方法
	public void method1(){
		System.out.println("test!!!");
	}
	private void method4(){
		System.out.println("test2!!!");
	}
	
	public abstract void method2();
	abstract void method3();
	//不能为private 编译报错
//	private abstract void method4();
}


