package com.howard.demo.jdk8;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * 测试方法的引用
 * @author Howard
 * 2017年4月14日
 */
public class TestMethodRef {
	public static void main(String[] args) {
		MethodRef r1 = (s) -> System.out.println(s);
		r1.test("普通方式");
		
		//使用方法的引用：实例方法的引用
		//System.out是一个实例  out是PrintStream 类型，有println方法
		MethodRef r2 = System.out::println;
		r2.test("方法引用");
		
		//MethodRef1 r3 =(a)-> Arrays.sort(a);
		//引用类方法
		MethodRef1 r3 = Arrays::sort;
		int[] a = new int[]{4,12,23,1,3};
		r3.test(a);
		//将排序后的数组输出
		r1.test(Arrays.toString(a));
		
		//引用类的实例方法
		MethodRef2 r4 = PrintStream::println;
		//第二个之后的参数作为引用方法的参数
		r4.test(System.out, "第二个参数");
		
		//引用构造器
		MethodRef3 r5 = String::new;
		String test = r5.test(new char[]{'测','试','构','造','器','引','用'});
		System.out.println(test);
		//普通情况
		MethodRef3 r6 = (c) -> {
			return new String(c);
		};
		String test2 = r6.test(new char[]{'测','试','构','造','器','引','用'});
		System.out.println(test2);
	}
}

interface MethodRef {
	void test(String s);
}

interface MethodRef1 {
	void test(int[] arr);
}

interface MethodRef2 {
	void test(PrintStream out,String str);
}
//测试构造器引用
interface MethodRef3 {
	String test(char[] chars);
}