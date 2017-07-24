package com.howard.demo.equal;
/**
 * 比较==和equals的区别
 * ==对于基本数据类型，比较的是数值，对于对象，比较得是引用所指向的对象所在的内存地址
 * equals比较的是对象的内容
 * xx.equals(yy),xx不能是null，所以一般"".equals(xx)可以避免空指针
 * @author Howard
 * 2017年3月8日
 */
public class TestEquals {
	public static void main(String[] args) {
		int a = 1;
		int b = 1;
		System.out.println("a==b:"+(a==b));
		
		String s1 = "abc";
		String s2 = "abc";
		System.out.println("s1==s2"+(s1==s2));
		
		String s3 = new String("abc");
		String s4 = new String("abc");
		System.out.println("s3==s4"+(s3==s4));
		System.out.println("s3.equal(s4)"+s3.equals(s4));
		
		String s5 = new String("abc").intern();
		System.out.println("s1==s5"+(s1==s5));
		
		Object obj = new Object();
	}
}
