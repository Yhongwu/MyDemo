package com.howard.demo.multithreading.exercise04;
/**
 * 每个哲学家可以进行思考或者吃饭，吃饭时需要先后获得右边和左边的筷子
 * 若没有同时获得右边和左边的筷子，则等待，
 * 若使用完之后就返回。
 * 2017年10月15日
 * @author hongwu
 */
public class Philosopher implements Runnable{
	private Chopstick right;
	private Chopstick left;
	private int index;
	private int thinkTime;
	public Philosopher(Chopstick right,Chopstick left,int index,int thinkingTime) {
		super();
		this.right = right;
		this.left = left;
		this.index = index;
		this.thinkTime = thinkingTime;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				System.out.println(this + "thinking...");
				thinking();
				System.out.println(this+" start to eat and take right stick");
				right.take();
				System.out.println(this+" take left stick");
				System.out.println(this+" eating");
				eating();
				right.drop();
				left.drop();
			}
		} catch (InterruptedException e) {
			//打印异常
			System.out.println(this+"InterruptedException");
		}
		
	}
	private void thinking() throws InterruptedException {
		Thread.sleep(thinkTime * 100);
	}
	private void eating() throws InterruptedException {
		Thread.sleep(thinkTime * 100);
	}
	@Override
	public String toString() {
		return "Philosopher [index=" + index + "]";
	}
	
}
