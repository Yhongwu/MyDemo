package com.howard.algorithm.newcoder.SwordRefersToOffer;

import java.util.Stack;
/**
 * 剑指offer
 * 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 * 用一个栈来保存最小的数
 * 可以有两种方法，一种额外空间为O(N),一种小于O(N),min的时间复杂度都是O(1)
 * 方法1：每次push时，同时往另一个栈push，如果当前push的数小于min栈的栈顶，则push新的数，否则再次push等于栈顶的数
 * 在pop时同步pop出，此时min栈的栈顶保存的一直是当前stack的最小的数
 * 方法2：每次push时，比较新的数与min栈的栈顶的大小，当小于等于栈顶时，则push进min栈，否则不做任何操作。
 * 在pop操作时，只有pop的数等于min栈的栈顶时，才从min栈的栈顶移除一个数，此时min栈的栈顶保存的一直是当前stack的最小的数
 * 2017年9月28日
 * @author hongwu
 */
public class MinStack {
	Stack<Integer> stack = new Stack<>();
	Stack<Integer> minStack = new Stack<>();
    public void push(int node) {
        stack.push(node);
        if (minStack.empty()) {
        	minStack.push(node);
        }else {
        	Integer peek = minStack.peek();
        	if (peek > node) minStack.push(node);
        }
    }
    
    public void pop() {
        Integer pop = stack.pop();
        if (pop == minStack.peek()) {
        	minStack.pop();
        }
    }
    
    public int top() {
        return minStack.peek();
    }
    
    public int min() {
        return minStack.peek();
    }
    
    public static void main(String[] args) {
		MinStack stack = new MinStack();
		stack.push(3);
		stack.push(4);
		System.out.println(stack.min());
	}
}
