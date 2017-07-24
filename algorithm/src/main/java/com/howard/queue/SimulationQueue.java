package com.howard.queue;

import java.util.Stack;
/**
 * 用两个栈来模拟队列操作
 * 这里使用java内置的Stack来实现
 * 建立两个栈a b，a为入队操作，b为出队操作
 * 入队：直接入队a即可
 * 出队：分两种情况考虑：
 * 1、若栈b不为空，则弹栈b即可
 * 2、若栈b为空，则一次弹栈a，将元素入栈b，全部完成后，执行第1步。
 * （同理。也可用两个队列模拟栈操作）
 * @author Howard
 * @param <E>
 * 2017年4月22日
 */
public class SimulationQueue<E> {
	private Stack<E> s1 = new Stack<>();
	private Stack<E> s2 = new Stack<>();
	/**
	 * 入队
	 * @param item
	 */
	public synchronized void put(E item) {
		s1.push(item);
	}
	/**
	 * 出队
	 * @return
	 */
	public synchronized E pop() {
		if (s2.isEmpty()) {
			while(!s1.isEmpty()) {
				s2.push(s1.pop());
			}
		}
		return s2.pop();
	}
	/**
	 * 判空
	 * @return
	 */
	public synchronized boolean isEmpty() {
		return s1.isEmpty() && s2.isEmpty();
	}
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		SimulationQueue<Integer> queue = new SimulationQueue<>();
		queue.put(1);
		queue.put(2);
		queue.put(3);
		queue.put(4);
		queue.put(5);
		System.out.println("队列第一个元素："+queue.pop());
		System.out.println("队列第一个元素："+queue.pop());
	}
}
