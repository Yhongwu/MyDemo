package com.howard.demo.equal;

public class StringEqualTest {
	public static void main(String[] args) {
		String a = "hello!Howard";
		String b = new String("hello!Howard");
		String c = "hello!"+"Howard";
		
		System.out.println("a==b "+(a == b));
		System.out.println("a==c "+(a == c));
		System.out.println("a equal b?"+a.equals(b));
		System.out.println("a equal c?"+a.equals(c));
		System.out.println("a.intern() == b.intern()?"+(a.intern() == b.intern()));
	}
}
