package com.howard.demo.multithreading.exercise04;
/**
 * 筷子
 * 每根筷子同时只能被一个哲学家获得，若有另外一个哲学家请求获得该筷子，则需要等待
 * 哲学家使用完筷子之后就放回并通知其他哲学家使用
 * 2017年10月15日
 * @author hongwu
 */
public class Chopstick {
	//筷子编号
	private int index;
	//是否正在使用
	private boolean using = false;
	
	public Chopstick(int index) {
		super();
		this.index = index;
	}

	@Override
	public String toString() {
		return "Chopstick [index=" + index + "]";
	}
	/**
	 * 拿起筷子
	 * 筷子被人拿走后，其他人只能等待此人放下筷子，才能重新拿起这只筷子
	 * @throws InterruptedException
	 */
	public synchronized void take() throws InterruptedException {
		while(using) {
			wait();
		}
		using = true;
	}
	/**
	 * 放下筷子
	 * 当有人放下这只筷子时，通知其他正在等待该筷子的人
	 */
	public synchronized void drop() {
		using = false;
		notifyAll();
	}
}
