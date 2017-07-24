package com.howard.demo.equal;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
/**
 * 测试hashcode和equals方法
 * @author Howard
 * 2017年2月24日
 */
public class EqualsTest {
	public static void main(String[] args) {
//		Book b1 = new Book();
//		Book b2 = new Book();
//		
//		b1.setName("java编程思想");
//		b1.setPageNum(880);
//		
//		b2.setName("java编程思想");
//		b2.setPageNum(880);
//		
//		List<Book> list = new LinkedList<>();
//		Set<Book> set = new HashSet<>();
//		
//		list.add(b1);
//		list.add(b2);
//		set.add(b1);
//		set.add(b2);
//		
//		System.out.println(b1.equals(b2));
//		System.out.println(b1==b2);
//		System.out.println(b1.hashCode());
//		System.out.println(b2.hashCode());
//		System.out.println("list:"+list.size());
//		System.out.println("set:"+set.size());
		
		Book b1 = new Book();
		
		b1.setName("java编程思想");
		b1.setPageNum(880);
		
		Set<Book> set = new HashSet<>(); //<>jdk1.7的语法
		System.out.println("改变前："+b1.hashCode());
		set.add(b1);
		System.out.println("set:"+set.size());
		b1.setPageNum(770);
		System.out.println("改变后："+b1.hashCode());
		set.remove(b1);
		System.out.println("set:"+set.size());
	}
}
