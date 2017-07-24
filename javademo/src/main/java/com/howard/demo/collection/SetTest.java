package com.howard.demo.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * set的两种遍历方式
 * @author Howard
 * 2017年3月2日
 */
public class SetTest {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		
		set.add("aa");
		set.add("bb");
		set.add("cc");
		set.add("dd");
		set.add("ee");
		//下面这个不会再添加进去 set不允许重复
		set.add("bb");
		
		System.out.println("set中的数量："+set.size());
		
		//遍历set的方法
		//增强for循环
		System.out.println("增强for循环：");
		for (String str:set) {
			System.out.print(str+"  ");
		}
		System.out.println();
		System.out.println("迭代器：");
		//迭代器
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next()+"  ");
		}
		//另
//		for(;iterator.hasNext();){
//			System.out.print(iterator.next()+"  ");
//		}
	}
}
