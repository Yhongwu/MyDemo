package com.howard.array;

import java.util.Arrays;
/**
 * 求int数组中两两相加等于某个数的组合种数
 * @author Howard
 * 2017年4月8日
 */
public class FindSumInArray {
	/**
	 * 方法1：
	 * 蛮力法
	 * 时间复杂度O（n^2）
	 * @param a
	 * @param sum
	 */
	public static void findSumInArray1(int a[],int sum) {
		int len = a.length;
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				if (a[i] + a[j] == sum) {
					System.out.println(a[i] +"," + a[j]);
				}
			}
		}
	}
	/**
	 * 方法2：
	 * 排序法，可以利用堆排序或者快排，此时时间复杂度为O（mlogn）
	 * 然后只需要同时从头和尾遍历数组，直到满足low>high 这个过程时间复杂度为O(n)
	 * 所以整个算法时间复杂度为o(nlogn)
	 * @param a
	 * @param sum
	 */
	public static void findSumInArray2(int a[],int sum) {
		//排序 排序算法最好的时间复杂度是O(nlogn)
		Arrays.sort(a);
		int low = 0;
		int high = a.length - 1;
		while (low < high) {
			if (a[low] + a[high] < sum) 
				low++;
			else if (a[low] + a[high] > sum) 
				high--;
			else {
				System.out.println(a[low] +"," + a[high]);
				low++;
				high--;
			}
		}
	}
	
	public static void main(String[] args) {
		int a[] = {1,7,17,2,6,3,14};
		findSumInArray1(a, 20);
		System.out.println();
		findSumInArray2(a, 20);
	}
}
