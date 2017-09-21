package com.howard.algorithm.NetEase;

import java.util.Scanner;
/**
 * 相反数
 * 网易2018校招编程题
 * 
 * 为了得到一个数的"相反数",我们将这个数的数字顺序颠倒,然后再加上原先的数得到"相反数"。例如,为了得到1325的"相反数",
 * 首先我们将该数的数字顺序颠倒,我们得到5231,之后再加上原先的数,我们得到5231+1325=6556.如果颠倒之后的数字有前缀
 * 零,前缀零将会被忽略。例如n = 100, 颠倒之后是1. 
 * 
 * 利用String数组反转即可
 * 2017年9月14日
 * @author hongwu
 */
public class OppositeNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long num = sc.nextLong();
		sc.close();
		StringBuffer sb = new StringBuffer(String.valueOf(num));
		sb.reverse();
		num = num + Long.parseLong(sb.toString());
		System.out.println(num);
	}
}
