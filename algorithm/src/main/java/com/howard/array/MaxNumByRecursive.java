package com.howard.array;
/**
 * 用递归求一个数组的最大元素
 * 思路：递归的求解数组的"第一个元素"与"数组中其它元素组成的子数组的最大值"的最大值
 * @author Howard
 * 2017年4月8日
 */
public class MaxNumByRecursive {
	private static int max(int m,int n) {
		return m > n? m :n;
	}
	public static Integer maxNum (int a[],int begin) {
		int length = a.length - begin;
		if (length == 1) 
			return a[begin];
		else {
			return max(a[begin], maxNum(a, begin + 1));
		}
	}
	public static void main(String[] args) {
		int a[] = {6,2,3,4,23,43,12,33};
		System.out.println(maxNum(a, 0));
	}
}
