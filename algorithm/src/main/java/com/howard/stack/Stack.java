package com.howard.stack;
/**
 * 栈结点元素结点
 * @author Howard
 * @param <E>
 * 2017年4月22日
 */
class Node<E> {
	Node<E> next = null;
	E data;
	public Node(E data) {
		this.data = data;
	}
	
}
/**
 * 栈
 * 链表实现
 * @author Howard
 * @param <E>
 * 2017年4月22日
 */
public class Stack<E> {
	Node<E> top = null;
	public boolean isEmpty() {
		return top == null;
	}
	/**
	 * 压栈
	 * @param data
	 */
	public void push(E data) {
		Node<E> newNode = new Node<E>(data);
		newNode.next = top;
		top = newNode;
	}
	/**
	 * 出栈
	 * 栈元素数量减1
	 * @return
	 */
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		E data = top.data;
		top = top.next;
		return data;
	}
	/**
	 * 返回栈顶元素
	 * @return
	 */
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return top.data;
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(12);
		System.out.println("栈顶元素："+stack.pop());
		System.out.println("栈顶元素："+stack.pop());
		
	}
}
