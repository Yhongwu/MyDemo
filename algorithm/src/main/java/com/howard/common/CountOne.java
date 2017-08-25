package com.howard.common;
/**
 * 给定一个整数 输出这个整数二进制表示中1的个数
 * 例如：7二进制111 则输出3
 * @author Howard
 * 2017年4月5日
 */
public class CountOne {
	/**
	 * 利用位运算
	 * 判断该数二进制最后一位是否为0 （通过n&1是否为1来判断）
	 * 如果为1 则计数器加1 然后右移丢弃最后一位 直到这个数为0为止
	 * 该算法时间复杂度为O(m) m为该数二进制的长度
	 * @param n
	 * @return
	 */
	public static int countOne1 (int n) {
		int count = 0;
		while (n > 0) {
			if ((n & 1) == 1) count++;
			n >>= 1;
		}
		return count;
	}
	
	/**
	 * 只考虑1的个数
	 * 利用n & (n - 1) 每计算一次  结果都会少了一位1 而且少的是最后一个1
	 * 该算法时间复杂度为O(m) m是该数二进制中1的个数
	 * 显然效率比上面那个的好
	 * @param n
	 * @return
	 */
	public static int countOne2 (int n) {
		int count = 0;
		while (n > 0) {
			if (n != 0) {
				n = n & (n - 1);
			}
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(countOne1(7)); //111
		System.out.println(countOne2(8)); //1000
	}
}
