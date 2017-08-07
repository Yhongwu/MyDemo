package com.howard.demo.multithreading.exercise01;
/**
 * 现在有T1、T2、T3三个线程,你怎样保证T2在T1执行完后执行,T3在T2执行完后执行
 * 提示:使用join
 * 2017年7月31日
 * @author hongwu
 */
public class ThreeJoin {
	public static void main(String[] args) {
		Thread t1 = new Thread(new T1(),"线程1");
		Thread t2 = new Thread(new T1(),"线程2");
		Thread t3 = new Thread(new T1(),"线程3");
		try {
			//使用join来保持t1,t2,t3的顺序
			t1.start();
			t1.join();
			t2.start();
			t2.join();
			t3.start();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class T1 implements Runnable{

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+ "开始执行");
			//模拟执行耗时
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName()+ "执行完成");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
