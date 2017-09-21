package com.howard.test.test01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * <? super E>和<? extends E>的区别
 * 部分编译不通过 故带注释
 * 2017年9月21日
 * @author hongwu
 */
public class Test1 {
	public static void main(String[] args) {
//		List<?> list= new ArrayList<People>(); 
//		list.add(new Man());
		
//		List<People> list= new ArrayList<Man>(); 
		
		List<? extends People> list_1 = null;
//		list_1 = new ArrayList<Man>();
		
//		People p = new People();
//		list_1.add(p);
//		People people = list_1.get(0);
		
//		list_1 = new ArrayList<People>();
//		list_1.add(new People());
//		list_1.add(new Man());
//		list_1.add(new Woman());
		
//		List<? extends Man> list_2 = null;
//		list_2 = new ArrayList<People>();
//		list_2 = new ArrayList<Boy>();
//		
//		list_2.add(new People());
//		list_2.add(new Man());
//		list_2.add(new Woman());
//		
//		List<? super People> list_3 = null;
//		list_3 = new ArrayList<People>();
//		list_3 = new ArrayList<Man>();
//		
//		list_3.add(new People());
//		list_3.add(new Man());
//		list_3.add(new Boy());
//		list_3.add(new Woman());
//		
//		List<? super Man> list_4 = null;
//		list_4 = new ArrayList<People>();//编译不报错
//		list_4 = new ArrayList<Boy>(); //编译报错
//		
//		list_4 = new ArrayList<Man>();//编译不报错
//		Object man = (Man)list_4.get(0);
//		Object woman = (Woman)list_4.get(0);
//		
////		list_4.add(new People());
//		
//		list_4.add(new Man());
//		list_4.add(new Boy());
//		list_4.add(new Woman()); //编译报错
//		
//		
//		List<Pear> pears = new ArrayList<Pear>();
//		p.produce(pears);
//		Producer<Pear> p = new Producer<>();
//		p.produce(apples); //编译不通过
//		
//		Consumer<RedApple> c = new Consumer<>();
//		List<RedApple> redApples = new ArrayList<RedApple>();
//		c.consume(redApples);
//		
//		List<Apple> apples = new ArrayList<Apple>();
//		c.consume(apples); //编译通过
//		
//		Collections.copy(dest, src);
	}
}
class People {
}
class Man extends People {
}
class Woman extends People {
}
class Boy extends Man {
}

class Fruit {
	//水果
}
class Apple extends Fruit{
	//苹果
}
class RedApple extends Apple{
	//红苹果
}
class Pear extends Fruit{
	//梨
}
//class Produce<E> {
//	public void produce(List<E> list) {
//		for (E e : list) { 
//			//生产...加入仓库...
//			System.out.println("批量生产...");
//		}
//	}
//}
class Producer<E> {
	public void produce(List<? extends E> list) {
		for (E e : list) { //利用<? extends E>读取的特性
			//生产...加入仓库...
		}
	    System.out.println("批量生产完成...");
	}
}
//消费者
class Consumer<E> {
	public E consume(List<? super E> list) {
		E e = (E) list.get(0); //模拟消费一个(感觉用队列比较合适)
		return e;
	}
}