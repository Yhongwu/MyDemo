package com.howard.iq;
/**
 * 汉诺塔问题
 * 基本解决思路：
 * 1 将a棒上的n-1个盘移到b上
 * 2 讲a上剩余的一个移到c上
 * 3 将b上n-1个盘移到到c上
 * 其中，每次移动n-1盘的方法，为重复上面3个步骤，故用递归可解
 * @author Howard
 * 2017年3月31日
 */
public class Hanoi {
	//移动次数
	static long count;
		
	public static void main(String[] args) {
		int n = 3;
		char a = 'A';
		char b = 'B';
		char c = 'C';
		System.out.println("汉诺塔圆盘数量："+n);
		HanoiAlgorithm(n, a, b, c);
		System.out.println("共需移动"+count+"次");
	}
	/**
	 * 将n个盘借助b从a移动到c
	 * @param n
	 * @param a
	 * @param b
	 * @param c
	 */
	public static void HanoiAlgorithm(int n,char a,char b,char c) {
		if (n == 1) {
			count++;
			System.out.println("第"+count+"次移动：\t圆盘从"+a+"移动到"+c);
		}else {
			HanoiAlgorithm(n-1, a, c, b);
			count++;
			System.out.println("第"+count+"次移动：\t圆盘从"+a+"移动到"+c);
			HanoiAlgorithm(n-1, b, a, c);
		}
	}
}
