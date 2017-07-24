package com.howard.iq;
/**
 * 鸡兔同笼问题
 * 很简单
 * 鸡有1头2脚 兔有1头4脚 
 * @author Howard
 * 2017年3月31日
 */
public class JTTL {
	public static void main(String[] args) {
		System.out.println("鸡兔同笼问题：");
		int head = 35;
		int foot = 94;
		JTTLAlgorithm(head, foot);
	}
	public static void JTTLAlgorithm(int head,int foot) {
		int chicken = 0;
		int rabbit = 0;
		rabbit = (foot - head * 2) /2;
		chicken = head - rabbit;
		System.out.println("鸡："+chicken+"只，兔："+rabbit+"只");
	}
}
