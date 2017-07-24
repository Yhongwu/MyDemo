package com.howard.common;
/**
 * 统计二进制0或1的个数
 * @author Howard
 * 2017年3月8日
 */
public class ProblemSets01 {
	
	public static void main(String[] args) {
		
		
		int count = func2(65530);
		System.out.println(count);
	}
	/**
	 * 按位与 
	 * 统计二进制1的位数
	 * @param x
	 * @return
	 */
	public static int func1(int x) {
		int countx = 0;
		while(x!=0) {
			countx++;
			x = x & (x-1);
		}
		return countx;
	}
	/**
	 * 按位或
	 * 统计二进制0的个数 
	 * 统计范围受循环体判断条件影响
	 * @param x
	 * @return
	 */
	public static int func2(int x) {
		int countx = 0;
		while(x != 65535) {
			countx++;
			x = x | (x + 1);
		}
		return countx;
	}
}
