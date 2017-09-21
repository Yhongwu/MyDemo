package com.howard.test;
/**
 * java中非静态内部类不能有staic修饰的属性，但可以有常量
 * 2017年9月12日
 * @author hongwu
 */
public class Test2 {
	static class innerClass {
		static int b = 0;  //编译不通过
		static final int c = 1; //编译通过
	}
}
class Test2_2 {
	static class innerClass {
		static int b = 0;  //编译通过
		static final int c = 1; //编译通过
	}
}
