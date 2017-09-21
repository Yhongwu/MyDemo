package com.howard.algorithm.SwordRefersToOffer;

import java.util.Stack;


/**
 * 剑指offer
 * 两个栈实现队列
 * 2017年9月15日
 * @author hongwu
 */
public class QueueWith2Stack {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
    	if (stack2.size() != 0) {
    		return stack2.pop();
    	}else {
    		//这里注意必须先保存长度 否则后面pop会把stack1的数取出，影响stack1.size();
    		int size = stack1.size();
    		for (int i = 0 ; i < size;  i ++ ) {
    			stack2.push(stack1.pop());
    		}
    		return stack2.pop();
    	}
    }
    
    public static void main(String[] args) {
    	QueueWith2Stack s  = new QueueWith2Stack();
    	s.stack1.push(1);
    	s.stack1.push(2);
    	s.stack1.push(3);
    	System.out.println(s.pop());
	}
}
