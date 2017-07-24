package com.howard.demo.jdk8;

public class TestLambdaReturn {
	void re(LambdaReturn lr) {
		int i = lr.test();
		System.out.println("lambda表达式返回值是："+i);
	}
	
	public static void main(String[] args) {
		TestLambdaReturn tlr = new TestLambdaReturn();
		tlr.re( () -> {return 14;});
		//如果执行语句只有一句，还可以简写为下面的形式
		tlr.re( () -> 14);
		
		int i = 1000;
		tlr.re( () -> i);
//		i = 10;
	}
}
interface LambdaReturn {
	int test();
}