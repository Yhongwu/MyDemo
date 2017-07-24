package com.howard.demo.jdk8;
@FunctionalInterface
interface A {
	default void test() {
		System.out.println("接口A的默认方法");
	}
	void test1();
}
interface B {
	default void test() {
		System.out.println("接口B的默认方法");
	}
}
interface C extends A,B {

	@Override
	default void test() {
		A.super.test();
	}
	default void test1() {
		System.out.println("在子接口实现父接口的抽象方法");
	}
	
}
class D implements A,B {
	@Override
	public void test() {
		A.super.test();
	}

	@Override
	public void test1() {
		System.out.println("类实现接口的方法");
		
	}
}
interface E {
	default void test() {
		System.out.println("接口E的默认方法");
	}
}
interface F extends E {
    void test();
}