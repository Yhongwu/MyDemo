package com.howard.demo.jdk8;

public class TestLambda {
	public static void main(String[] args) {
		TestLanmdaInterface1 t1 = new TestLanmdaInterface1() {
			@Override
			public void test() {
				System.out.println("使用匿名内部类");
				
			}
		};
		//与上面的匿名内部类执行效果一样
		//右边的类型会自动根据左边的类型进行判断
		TestLanmdaInterface1 t2 = () -> {
			System.out.println("使用lanbda");
		};
		t1.test();
		t2.test();
		
		//如果执行语句只有一行，可以省略大括号
		TestLanmdaInterface1 t3 = () -> System.out.println("省略执行语句大括号，使用lanbda");
		t3.test();
		
		TestLanmdaInterface2 t4 = (s) -> System.out.println("使用lanbda表达式，带1个参数，参数为："+s);
		t4.test("字符串参数1");
		
		TestLanmdaInterface2 t5 = s -> System.out.println("使用lanbda表达式，只带1个参数，可省略参数的圆括号，参数为："+s);
		t5.test("字符串参数2");
		
		TestLanmdaInterface3 t6 = (s,i) -> System.out.println("使用lanbda表达式，带两个参数，不可以省略圆括号，参数为："+s+"  "+ i);
		t6.test("字符串参数3",50);
	}
}

@FunctionalInterface
interface TestLanmdaInterface1 {
	//不带参数的抽象方法
	void test();
}
@FunctionalInterface
interface TestLanmdaInterface2 {
	//带参数的抽象方法
	void test(String str);
}
@FunctionalInterface
interface TestLanmdaInterface3 {
	//带多个参数的抽象方法
	void test(String str,int num);
}