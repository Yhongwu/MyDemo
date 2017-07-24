package com.howard.common;
/**
 * 交换变量的三种方式
 * @author Howard
 * 2017年3月30日
 */
public class Swap {
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		
		//第一种：
		int temp = a;
		a = b;
		b = temp;
		System.out.println(a+"  "+b);
		
		//第二种：
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println(a+"  "+b);
		
		//第三种： （x^y^y） == x
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a+"  "+b);
	}
}
