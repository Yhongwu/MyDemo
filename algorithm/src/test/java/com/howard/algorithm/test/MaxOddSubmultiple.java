package com.howard.algorithm.test;

import java.util.Scanner;

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
