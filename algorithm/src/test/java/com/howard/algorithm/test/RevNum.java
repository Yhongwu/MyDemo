package com.howard.algorithm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * [编程题] 数字翻转
 * 时间限制：1秒
 * 空间限制：32768K
 * 对于一个整数X，定义操作rev(X)为将X按数位翻转过来，并且去除掉前导0。例如:
 * 如果 X = 123，则rev(X) = 321;
 * 如果 X = 100，则rev(X) = 1.
 * 现在给出整数x和y,要求rev(rev(x) + rev(y))为多少？
 * 输入描述:
 * 输入为一行，x、y(1 ≤ x、y ≤ 1000)，以空格隔开。
 * 输出描述:
 * 输出rev(rev(x) + rev(y))的值
 * 输入例子1:123 100
 * 输出例子1:223
 * 
 * 2017年8月12日
 * @author hongwu
 */
public class RevNum {
	
	public static int rev(int num) {
		List<Integer> list = new ArrayList<>();
		int newNum = 0;
		while (num > 0) {
			int x = num%10;
			num = num/10;
			list.add(x);
		}
		int z = 1;
		for (int i = list.size() - 1 ; i >= 0 ; i -- ) {
			newNum += list.get(i) * z;
			z *= 10;
		}
		
		return newNum;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		System.out.println(rev(rev(x) + rev(y)));
		scanner.close();
	}
}
