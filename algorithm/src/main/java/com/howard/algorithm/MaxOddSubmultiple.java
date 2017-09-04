package com.howard.algorithm;

import java.util.Scanner;
/**
 * 小易是一个数论爱好者，并且对于一个数的奇数约数十分感兴趣。一天小易遇到这样一个问题： 定义函数f(x)为x最大的奇数约数，x为正整数。 例如:f(44) = 11.
 * 现在给出一个N，需要求出 f(1) + f(2) + f(3).......f(N)
 * 例如： N = 7
 * f(1) + f(2) + f(3) + f(4) + f(5) + f(6) + f(7) = 1 + 1 + 3 + 1 + 5 + 3 + 7 = 21
 * 小易计算这个问题遇到了困难，需要你来设计一个算法帮助他。 
 * 2017年8月31日
 * @author hongwu
 */
public class MaxOddSubmultiple {
	public static void main(String[] args) {
//		int num = 7;
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		scanner.close();
		int result = 0;
		for (int i = 1 ; i <= num; i ++ ) {
			result += getMaxOddSubmultiple(i);
		}
		System.out.println(result);
	}
	public static int getMaxOddSubmultiple(int num) {
		int result = 1;
		if (num % 2 != 0) 
			return num;
		else {
			int y = (int) Math.sqrt(num);
			if (y % 2 == 0) {
				return y;
			}
			for (int i = y - 1 ; i >= 1; i -= 2) {
				if (num % i == 0) {
					result = i;
					break;
				}
			}
		}
		return result;
		
	}
}
