package com.howard.array;
/**
 * 循环右移数组k位
 * 假设有 1，2，3，4，5，6，7，8，9，10 循环右移3位
 * 8，9，10，1，2，3，4，5，6，7
 * 可以分为下面三步：
 * 1)逆序1234567 则为76543218910
 * 2)逆序8910 则为 76543211098
 * 3)逆序整个数组 ，8，9，10，1，2，3，4，5，6，7
 * 该算法进行了三次逆序，因此算法的时间复杂度为O(n)
 * @author Howard
 * 2017年4月8日
 */
public class ShiftK {
	/**
	 * 逆序
	 * @param a
	 * @param low
	 * @param high
	 */
	public static void reverse(int a[],int low,int high) {
		while (low < high) {
			int temp = a[high];
			a[high] = a[low];
			a[low] = temp;
			low ++;
			high --;
		}
	}
	/**
	 * 循环右移k位
	 * @param a
	 * @param k
	 */
	public static void shift_k (int a[],int k) {
		int len = a.length;
		//为防止k大于len，所以先取余 结果不变
		k = k % len;
		reverse(a, len - k, len - 1);
		reverse(a, 0, len - k -1);
		reverse(a, 0, len - 1);
	}
	
	public static void main(String[] args) {
		int a[] = {1,2,3,4,5,6,7,8,9,10};
		shift_k(a, 3);
		for (int i : a) {
			System.out.print(i+" ");
		}
	}
}
