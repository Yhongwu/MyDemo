package com.howard.queue;

class Node<E> {
	Node<E> next = null;
	E data;
	public Node(E data) {
		this.data = data;
	}
}
/**
 * 队列
 * 链表实现
 * @author Howard
 * @param <E>
 * 2017年4月22日
 */
public class MyQueue<E> {
	//队头
	private Node<E> head = null;
	//队尾
	private Node<E> tail = null;
	public boolean isEmpty() {
		return head == tail;
	}
	/**
	 * 入队
	 * @param data
	 */
	public void put(E data) {
		Node<E> newNode = new Node<>(data);
		//队列为空
		if (head == null && tail == null) {
			head = tail = newNode;
		}else {
			tail.next = newNode;
			tail = newNode;
		}
	}
	/**
	 * 出队
	 * @return
	 */
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		E data = head.data;
		head = head.next;
		return data;
	}
	/**
	 * 队列元素数量
	 * @return
	 */
	public int size() {
		Node<E> tmp = head;
		int n = 0;
		while(tmp != null) {
			n++;
			tmp = tmp.next;
		}
		return n;
	}
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<>();
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
