package com.howard.demo.jdk8;

public interface Integerface1 {
	void test();
}

@FunctionalInterface
interface Integerface2 {
	void test();
}

interface Integerface3 {
	void test();
	void test2();
}
//函数式接口
@FunctionalInterface
interface TestStaticMethod {
	//这是一个抽象方法
	void test();
	//静态方法，不是抽象方法
	static void test1() {
		System.out.println("接口里的静态方法！");
	}
}
//非静态default方法
interface TestDefaultMethod{
	default void test() {
		System.out.println("这个是接口里的default方法test");
	}
	public default void test1() {
		System.out.println("这个是接口里的default方法test1");
	}
	//编译报错
//	private default void test2() {
//		System.out.println("这个是接口里的default方法");
//	}
}
