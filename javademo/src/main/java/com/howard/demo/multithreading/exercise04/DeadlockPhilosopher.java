package com.howard.demo.multithreading.exercise04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 会产生死锁的版本
 * 5个哲学家，5只筷子，每个哲学家吃饭之前需要先拿到右边的筷子，然后再拿到左边的筷子
 * 之后才能吃饭
 * 2017年10月15日
 * @author hongwu
 */
public class DeadlockPhilosopher {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();
		int size = 5; //5只筷子 5个哲学家
		int thinkingTime = 0; //思考时间0秒
		Chopstick[] chopsticks = new Chopstick[size];
		for (int i = 0; i < size; i ++ ) {
			chopsticks[i] = new Chopstick(i);
		}
		for (int i = 0 ; i < size; i ++) {
			executor.execute(new Philosopher(chopsticks[i], chopsticks[(i+1)%size], i, thinkingTime));
		}
		Thread.sleep(4*1000);
		executor.shutdown();
	}
}
