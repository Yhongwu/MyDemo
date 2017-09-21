package com.howard.demo.constructobject;
/**
 * 不通过new方式创建对象 方法2：
 * 通过反射机制创建对象
 * 调用了构造方法
 * 2017年8月29日
 * @author hongwu
 */
public class Test2 {
	public static void main(String[] args) {
		Class clazz;
		try {
			clazz = Class.forName("com.howard.demo.constructobject.Person");
			Person p = (Person) clazz.newInstance();
			System.out.println(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Person {
	String name = "howard";
	public Person() {
		System.out.println("construct");
	}
	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
}
