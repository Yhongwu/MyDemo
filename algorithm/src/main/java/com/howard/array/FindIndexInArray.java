package com.howard.array;
/**
 * 求指定数字在数组中第一次出现的位置
 * 给定数组，且规定这个数组中相邻的元素只差都是1，给定某个数，求它在数组中第一次出现的位置	
 * @author Howard
 * 2017年4月8日
 */
public class FindIndexInArray {
	/**
	 * 跳跃搜索法：
	 * 利用给定的条件：相邻之间的元素只差都是1，则如果当前数组下标0，数是2,
	 * 如果查找的是7，那么7至少出现在0+（7-2） = 5个位置
	 * 该算法比蛮力法效率更高些
	 * @param a
	 * @param t
	 * @return
	 */
	public static int findIndex (int a[],int t) {
		if (a == null) 
			return -1;
		int i = 0;
		while (i < a.length) {
			if (a[i] == t) {
				return i;
			}else {
				i += Math.abs(t - a[i]);
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		int a[] = {3,4,5,6,7,8,9,8};
		System.out.println(findIndex(a, 5));
	}
}
