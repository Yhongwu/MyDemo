package com.howard.array;
/**
 * 找出数组中第k个最小的数
 * @author Howard
 * 2017年4月8日
 */
public class FindKMin {
	/**
	 * 快速选择法 
	 * 采用的是快速排序的思想，选择数组中的一个数tmp作为快排的枢纽，
	 * 按快排那样把比它小的都放在它左边，比它大的都放在它右边，
	 * 最后判断tmp是不是第k-1个数(由于数组是从0开始的),如果是，那么它就是第k个最小的数
	 * 如果不是，那么如果它的位置小于k-1.则只需要在数组左边继续重复一样的步骤进行查找，
	 * 如果比k-1大，那么第k大的数在数组右边，所以在数组右边继续查找。
	 * 表面上看起来是快排。但是由于不需要对数组全部排序，只需要排序查找的数所在的那部分，每次规模减半，
	 * 所以整体上效率比快排快
	 * @param a
	 * @param low
	 * @param high
	 * @param k
	 * @return
	 */
	public static int quickSort(int a[],int low,int high,int k) {
		int tmp = a[low];
		int newlow = low;
		int newhigh = high;
		while(low < high){
			if (a[high] >= tmp && low < high) {
				high--;
			}
			a[low] = a[high];
			if (a[low] <= tmp && low < high) {
				low++;
			}
			a[high] = a[low];
		}
		a[low] = tmp;
		if (low  == k ) 
			return tmp;
		else if (low  > k)
			return quickSort(a, newlow, low-1, k);
		else return quickSort(a, low+1, newhigh, k);
	}
	
	public static int findKMin (int a[],int k) {
		if (a == null) 
			return Integer.MIN_VALUE;
		if (k <= 0 || k > a.length)
			return Integer.MIN_VALUE;
		return quickSort(a, 0, a.length-1, k - 1);
	}
	
	public static void main(String[] args) {
		int a[] = {1,5,2,7,8,0,6};
		System.out.println(findKMin(a, 4));
	}
}
