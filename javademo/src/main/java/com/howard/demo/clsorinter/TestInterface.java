package com.howard.demo.clsorinter;
/**
 * 接口
 * @author Howard
 * 2017年2月17日
 */
public interface TestInterface {
	//不能有普通成员变量 编译报错
//	int a;
	//可以有static变量，但必须赋予初值，且默认为public static final类型
	static int a = 1;
	
	public void method1();
	void method2();
	//不能为private 编译报错
//	private void method3();
	
}
