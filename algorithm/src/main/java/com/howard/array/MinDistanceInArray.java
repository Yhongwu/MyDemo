package com.howard.array;
/**
 * 求数组中元素的最小距离
 * 思路：用minDistance记录最小的
 * 遍历一次数组，遇到n1时记录n1对应的下标，并判断是否已经记录了n2的下标，若有，则与minDistance进行比较找出小的
 * 遇到n2时同样处理
 * 由于只遍历了一次数组，所以时间复杂度是O(n)
 * @author Howard
 * 2017年4月8日
 */
public class MinDistanceInArray {
	private static int min(int m,int n) {
		return m > n ? n:m;
	}
	public static Integer minDistance(int a[],int n1,int n2) {
		if (a == null) 
			return null;
		int len = a.length;
		int index_1 = -1;
		int index_2 = -1;
		int minDistance = -10000;
		for (int i = 0; i < len; i++) {
			if (a[i] == n1) {
				index_1 = i;
				if (index_2 >= 0) {
					minDistance = min(Math.abs(minDistance),Math.abs(index_1 - index_2));
				}
			}
			if (a[i] == n2) {
				index_2 = i;
				if (index_1 >= 0) {
					minDistance = min(Math.abs(minDistance),Math.abs(index_2 - index_1));
				}
			}
		}
		return minDistance;
	}
	public static void main(String[] args) {
		int a[] = {4,5,6,4,7,6,4,7,8,5,6,4,3,10,8};
		System.out.println(minDistance(a, 4, 8));
	}
}
