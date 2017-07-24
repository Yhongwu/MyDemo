package com.howard.queue;

import java.util.LinkedList;
/**
 * 队列
 * 数组实现
 * 这里方法都加了synchronized是为了保证多线程时可以同步
 * @author Howard
 * @param <E>
 * 2017年4月22日
 */
public class Queue<E> {
	private LinkedList<E> list = new LinkedList<>();
	private int size = 0;
	/**
	 * 入队
	 * @param data
	 */
	public void put(E data) {
		list.add(data);
		size++;
	}
	/**
	 * 出队
	 * @return
	 */
	public synchronized E pop() {
		size -- ;
		return list.removeFirst();
	}
	/**
	 * 判空
	 * @return
	 */
	public synchronized boolean isEmpty() {
		return size == 0;
	}
	/**
	 * 队列元素数目
	 * @return
	 */
	public synchronized int size() {
		return size;
	}
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<>();
		queue.put(1);
		queue.put(2);
		queue.put(3);
		queue.put(4);
		queue.put(5);
		System.out.println("队列长度："+queue.size());
		System.out.println("队列第一个元素："+queue.pop());
		System.out.println("队列第一个元素："+queue.pop());
	}
}
