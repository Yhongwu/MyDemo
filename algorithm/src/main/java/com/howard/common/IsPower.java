package com.howard.common;
/**
 * 判断一个数是否为2的n次方
 * @author Howard
 * 2017年4月5日
 */
public class IsPower {
	/**
	 * 利用移位 这里的左移相当于乘以2
	 * 时间复杂度O(logn)
	 * @param n
	 * @return
	 */
	public static boolean isPower1(int n) {
		if (n < 1) return false;
		int i = 1;
		while (i <= n) {
			if (i == n) 
				return true;
			i <<= 1;
		}
		return false;
	}
	/**
	 * 一个数如果是2的n次方，那么这个数二进制中只有一位是1，其余是0
	 * 如果该数减1，则其二进制与上面的完全不同，
	 * 利用这点，如果n是2的n次方，那么n&(n-1)必为0
	 * @param n
	 * @return
	 */
	public static boolean isPower2(int n) {
		if (n < 1) return false;
		int m = n & (n - 1);
		return m == 0;
	}
	
	public static void main(String[] args) {
		System.out.println(isPower1(4));
		System.out.println(isPower2(6));
	}
}
