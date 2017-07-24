package com.howard.array;
/**
 * 判断一个数组中的数值是否连续
 * 已知：元素取值只可能是0~65535中的任一个整数，且不会重复出现,除了0，0可以出现多次
 * 判断从一个n个数的数组是否连续
 * 注意：n个数可以乱序，例如{2,3,1,4}
 * 0可以通配任意一个数，如{2，3，4，0}这里可以当作1或5，因此该数组是连续的
 * 0可以出现多次
 * 可以出现一次也可以全部是0
 * @author Howard
 * 2017年4月8日
 */
public class ContinuousArray {
	/**
	 * 思路：
	 * 如果不存在0，那么只要满足最大值和最小值只差等于n-1即可，
	 * 但是存在0的情况下，只要非0最大值与非0最小值之差小于n-1即可
	 * 该算法时间复杂度O(n)
	 * @param a
	 * @return
	 */
	public static boolean isContinuous (int a[]) {
		int min = -1;
		int max = -1;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != 0) {
				if (min > a[i] || min == -1)
					min = a[i];
				if (max < a[i] || max == -1)
					max = a[i];
			}
		}
		if (max - min > a.length - 1) 
			return false;
		else return true;
	}
	
	public static void main(String[] args) {
		int a1[] = {0,3,2,6,8,5,0};
		int a2[] = {0,0,0,0};
		System.out.println(isContinuous(a1));
		System.out.println(isContinuous(a2));
	}
}
