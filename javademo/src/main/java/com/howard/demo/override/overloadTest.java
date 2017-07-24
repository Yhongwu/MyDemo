package com.howard.demo.override;

import java.util.Arrays;
import java.util.List;

/**
 * 简单测试重载（overload）
 * 参数个数或参数类型或参数顺序不同 参数顺序不同必须保证参数类型不同
 * 与修饰符和返回值无关  
 * @author Howard
 * 2017年2月9日
 */
public class overloadTest {
	
	public static void main(String[] args) {
		E ee = new E();
		System.out.println(ee.method());
		System.out.println(ee.method(0));
		System.out.println(ee.method(0, 1.0F));
		System.out.println(ee.method(1.0F, 0));
		System.out.println(ee.method(0, 'a'));
		
		//下面不属于overload测试
		//???不能这么写吗
		List<Integer> list1 = Arrays.asList(1,2,3,4);
//		List<Integer> list2 = [1,2,3,4];
		
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		System.out.println(c==d); //==用于比较两个对象的内存地址 
		System.out.println(e==f); 
		System.out.println(c==(a+b));
		System.out.println(c.equals(a+b));
		System.out.println(g==(a+b));
		System.out.println(g.equals(a+b));
		
	}
	
}
class E{
	public int method(){
		System.out.println("调用无参方法,返回int");
		return 1;
	}
	public int method(int i){
		System.out.println("调用带int参方法"+i+",返回int");
		return 2;
	}
	public int method(int i,float j){
		System.out.println("调用带int、float参方法"+i+" "+j+",返回int");
		return 3;
	}
	public int method(float j,int i){
		System.out.println("调用带float、int参方法"+j+" "+i+",返回int");
		return 4;
	}
	float method(int i,char a){
		System.out.println("调用带int、char参方法"+i+" "+a+",返回float");
		return 5.0F;
	}
	
}