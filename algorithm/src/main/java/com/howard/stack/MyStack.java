package com.howard.stack;

import java.util.Arrays;
/**
 * 栈
 * 数组实现
 * @author Howard
 * @param <E>
 * 2017年4月22日
 */
public class MyStack<E> {
	 private Object[] stack;
	 private int size;
	 /**
	  * 初始化栈
	  * 默认容量10
	  */
	 public MyStack() {
		 stack = new Object[10];
	 }
	 /**
	  * 判断栈是否为空
	  * @return
	  */
	 public boolean isEmpty() {
		 return size == 0;
	 }
	 /**
	  * 返回栈顶元素
	  * @return
	  */
	 public E peek() {
		 if (isEmpty()) {
			 return null;
		 }
		 return (E) stack[size-1];
	 }
	 /**
	  * 弹栈
	  * 取出栈顶元素
	  * @return
	  */
	 public E pop() {
		 E e = peek();
		 stack[size - 1] = null;
		 size -- ;
		 return e;
	 }
	 /**
	  * 判断栈是否已经满了，若满了，则扩容
	  * 默认扩容1倍
	  * @param size
	  */
	 private void ensureCapacity(int size) {
		 int len = stack.length;
		 if (size > len) {
			 int newLen = 10;
			 stack = Arrays.copyOf(stack, len + newLen);
		 }
	 }
	 /**
	  * 压栈
	  * @param item
	  * @return
	  */
	 public E push(E item) {
		 ensureCapacity(size + 1);
		 stack[size ++ ] = item;
		 return item;
	 }
	 
	 public static void main(String[] args) {
		MyStack<Integer> stack = new MyStack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		
		stack.push(10);
		stack.push(11);
		stack.push(12);
		System.out.println("栈中元素个数："+stack.size);
		System.out.println("栈顶元素："+stack.pop());
		System.out.println("栈顶元素："+stack.pop());
		
	}
	 
}
