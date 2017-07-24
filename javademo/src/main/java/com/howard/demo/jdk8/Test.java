package com.howard.demo.jdk8;

public class Test {
	public static void main(String[] args) {
		//TestStaticMethod.test1();
		
		//使用匿名内部类初始化实例
//		TestDefaultMethod tx = new TestDefaultMethod() {
//		};
//		tx.test();
//		tx.test1();
//		C c = new C() {
//			@Override
//			public void test1() {
//				System.out.println("调用时重写");
//			}
//		};
//		c.test1();
		
//		D d = new D();
//		d.test();
		
		
//		E e = new E(){
//			
//		};
//		e.test();
		
		F f = new F(){
			@Override
			public void test() {
				System.out.println("F接口实现");
			}
		};
		f.test();
	}
}
