package com.howard.demo.multithreading.exercise01;
/**
 * 现在有T1、T2、T3三个线程,怎样保证T2在T1执行完后执行,T3在T2执行完后执行
 * 提示:使用join
 * 2017年7月31日
 * @author hongwu
 */
public class ThreeJoin2 {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
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
		},"线程1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					t1.join();
					System.out.println(Thread.currentThread().getName()+ "开始执行");
					//模拟执行耗时
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName()+ "执行完成");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		},"线程2");
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					t2.join();
					System.out.println(Thread.currentThread().getName()+ "开始执行");
					//模拟执行耗时
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName()+ "执行完成");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		},"线程3");
		
		t1.start();
		t2.start();
		t3.start();
	}
}
