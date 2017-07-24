package com.howard.stack;
/**
 * 用O(1)的时间复杂度求栈中最小的元素
 * 一般办法，要遍历栈中的所有元素，时间复杂度O(n)
 * 我们可以利用空间换时间，即在建立栈的时候多建一个用于存储最小的值，
 * 以后每次压栈或者弹栈时候都要进行比较以确定是否要压入或者弹出当前最小值
 * @author Howard
 * 2017年4月22日
 */
public class SaveMinStack {
	Stack<Integer> elem;
	Stack<Integer> min;
	public SaveMinStack() {
		elem = new Stack<>();
		min = new Stack<>();
	}
	/**
	 * 压栈
	 * @param data
	 */
	public void push(int data) {
		elem.push(data);
		if (min.isEmpty()) {
			min.push(data);
		}else {
			if (data < min.peek()) {
				min.push(data);
			}
		}
		
	}
	/**
	 * 获取当前栈的最小值
	 * @return
	 */
	public int min() {
		if (min.isEmpty()) {
			return Integer.MAX_VALUE;
		}else {
			return min.peek();
		}
	}
	/**
	 * 弹栈
	 * @return
	 */
	public int pop() {
		int topData = elem.peek();
		elem.pop();
		if (topData == this.min()) {
			min.pop();
		}
		return topData;
	}
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		SaveMinStack s = new SaveMinStack();
		s.push(5);
		s.push(6);
		s.push(1);
		s.push(2);
		
		System.out.println("当前栈最小值："+s.min());
		System.out.println("弹栈："+s.pop());
		System.out.println("当前栈最小值："+s.min());
		System.out.println("弹栈："+s.pop());
		System.out.println("当前栈最小值："+s.min());
	}
}
