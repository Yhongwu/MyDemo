package com.howard.iq;
/**
 * 猴子吃桃问题
 * 每天吃掉其中一半再多吃一个，第二天吃掉剩下的一半 ...第n天时猴子发现只有一个桃子了
 * 问最初有多少桃子
 * @author Howard
 * 2017年3月31日
 */
public class Peach {
	public static void main(String[] args) {
		int day = 10;
		int peach = PeachAlgorithm(day);
		System.out.println("天数为:"+day+"时，最初的桃子数为："+peach);
	}
	/**
	 * 递归算法解决
	 * @param n
	 * @return
	 */
	public static int PeachAlgorithm(int n) {
		if (n == 1) {
			return 1;
		}else {
			return (PeachAlgorithm(n-1) + 1) * 2;
		}
 	}
}
