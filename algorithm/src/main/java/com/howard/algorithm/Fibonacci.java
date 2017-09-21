package com.howard.algorithm;

import java.util.Scanner;

/**
 * 斐波那契数列
 * 1 1 2 3 5 8 ...
 * F1=1，F2=1，Fn=F(n-1)+F(n-2)（n>=2，n∈N*）。
 * 常见问：前n项的和 或 第n个斐波那契数是？
 * 2017年9月14日
 * @author hongwu
 */
public class Fibonacci {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		
		System.out.println("递归算法(第n个数是)："+fibonacci_1(n));
		System.out.println("递推算法(第n个数是)："+fibonacci_2(n));
	}
	/**
	 * 递归
	 * 时间复杂度O(2^n)
	 * @param n
	 * @return
	 */
	public static int fibonacci_1(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return 1;
		}else {
			return fibonacci_1(n-1) + fibonacci_1(n-2);
		}
		
	}
	/**
	 * 递推
	 * 时间复杂度o(n)
	 * @param n
	 * @return
	 */
	public static int fibonacci_2(int n) {
		int t1 = 1;
		int t2 = 1;
		int tn = 0;
		if (n == 0) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return 1;
		}
		for (int i = 2 ; i < n; i ++) {
			tn = t1 + t2;
			t1 = t2;
			t2 = tn;
		}
		return tn;
	}
	
	
}
