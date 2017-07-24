package com.howard.demo.finall;
/**
 * 测试finally返回的时机 
 * 这return中间  由测试3明显看出
 * finally 中的代码比 return 和 break 语句后执行
 * @author Howard
 * 2017年3月8日
 */
public class TestFinally {
	
	public static void main(String[] args) {
//		System.out.println(Test1());
//		System.out.println(Test2());
		System.out.println(Test3());
	}
	
	//返回1 这里编译器会提示有问题
	@SuppressWarnings("finally")
	static int Test1(){
		int x = 0;
		try{
			return x;
		}finally{
			return ++x;
		}
	}
	//返回2
	@SuppressWarnings("finally")
	static int Test2(){
		try{
			return 1;
		}finally{
			return 2;
		}
	}
	//结果：
	/*
	 fun1
	 fun2
	 2
	 */
	@SuppressWarnings("finally")
	static int Test3(){
		try{
			return fun1();
		}finally{
			return fun2();
		}
	}
	public static int fun1(){
		System.out.println("fun1");
		return 1;
	}
	public static int fun2(){
		System.out.println("fun2");
		return 2;
	}
	
}
