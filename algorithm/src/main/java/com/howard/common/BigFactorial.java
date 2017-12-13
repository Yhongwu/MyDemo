package com.howard.common;
/**
 * 阶乘计算
 * 满足大数下的阶乘计算不越界
 * @author yaohongwu
 *
 */
public class BigFactorial {
	public static void main(String[] args) {
		//50! 100位
		//100! 200位
		int n = 100;
		int[] num = new int[200];
		num[num.length-1] = 1;
		
		for (int i = 1 ; i <= n; i ++ ) {
			multi(num, i);
		}
		printWithoutZero(num);
	}

	/**
	 * 大数乘法计算
	 * 将乘法结果存储在数组里 可防止数字过大导致越界
	 * 需要预先估计结果多少位
	 * @param m
	 * @param n
	 */
	public static void multi(int m,int n) {
		//以256 * 16 位例子
		//预计结果不超过16位
		int[] num = new int[16];
		int j = num.length - 1;
		//将其中一个乘数映射到int数组 0000000000000256
		while (m != 0) {
			if (j <= num.length - 1) {
				num[j --] = m % 10;
				m /= 10;
			}
		}
		//对int数组的每一个数 分别乘以乘数16
		//结果： 0 0 0 0 0 0 0 0 0 0 0 0 0 32 80 96
		for (int i = 0 ; i < num.length; i ++) {
			num[i] = num[i] * n;
		}
		//由于后面几个超过了10 需要进位 从后面往前，每一个只保留个位，其余数字加到上一位
		//如 96 则保留 6 ，9加到80=86 ，86保留6，8加到32=40 40保留0，4加到上一位0=4 结果4096
		for (int i = num.length - 1; i > 0 ; i -- ) {
			num[i - 1] = num[i - 1] + num[i] / 10;
			num[i] %= 10;
		}
		for (int i = 0 ; i < num.length; i ++ ) {
			System.out.print(num[i]);
		}
		
	}
	public static void multi(int[] num,int n) {
		for (int i = 0 ; i < num.length; i ++) {
			num[i] = num[i] * n;
		}
		for (int i = num.length - 1; i > 0 ; i -- ) {
			num[i - 1] = num[i - 1] + num[i] / 10;
			num[i] %= 10;
		}
	}
	/**
	 * 输出计算结果
	 * 去除前面的0
	 * @param num
	 */
	public static void printWithoutZero(int[] num) {
		for (int i : num) {
			if (i != 0) {
				System.out.print(i);
			}
		}
		System.out.println();
	}
	/**
	 * 输出计算结果
	 * 包含前面的0
	 * 建议先用此测试数组是否足够长以容纳结果
	 * @param num
	 */
	public static void printWithZero(int[] num) {
		for (int i : num) {
			if (i != 0) {
				System.out.print(i);
			}
		}
		System.out.println();
	}
}
