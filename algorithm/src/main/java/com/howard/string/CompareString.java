package com.howard.string;

import java.util.Arrays;
/**
 * 判断两个字符串是否由相同字符组成
 * 相同字符指组成两个字符串的字符和字符个数是一样的
 * 假设字符在ascll范围内
 * @author Howard
 * 2017年4月9日
 */
public class CompareString {
	/**
	 * 方法1：
	 * 排序法
	 * 先对字符串的字符进行排序
	 * 然后直接比较两字符串是否相等
	 * 时间复杂度主要看排序的时间复杂度，由于排序最快时间复杂度为O(nlogn)
	 * 所以该算法时间复杂度为O(nlogn)
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean compareString1(String str1,String str2) {
		byte[] b1 = str1.getBytes();
		byte[] b2 = str2.getBytes();
		Arrays.sort(b1);
		Arrays.sort(b2);
		str1 = new String(b1);
		str2 = new String(b2);
		if (str1.equals(str2)) 
			return true;
		return false;
	}
	/**
	 * 方法2：空间换时间
	 * 申请数组空间长度为ascll长度的int数组，初始化为0
	 * 对字符串1只要出现的字符，对应位置加1
	 * 对字符串2只要出现的字符，对应位置减1
	 * 最后遍历数组，只要出现一个不为0，则返回false
	 * 该算法提高了效率，时间复杂度为O(n)
	 * 但是需要额外的空间
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean compareString2(String str1,String str2) {
		byte[] b1 = str1.getBytes();
		byte[] b2 = str2.getBytes();
		int all[] = new int[256];
		for (int i = 0; i < all.length; i++) {
			all[i] = 0;
		}
		for (int i = 0; i < b1.length; i ++) {
			//b1[i]-'0'得到ascll对应的数字
			all[b1[i]-'0'] ++;
		}
		for (int i = 0; i < b2.length; i ++) {
			all[b2[i]-'0'] --;
		}
		for (int i = 0 ; i < all.length; i++) {
			if (all[i] != 0) 
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String str1 = "aaabbaac";
		String str2 = "babaacaa";
		String str3 = "babaacac";
		System.out.println(compareString2(str1, str2));
		System.out.println(compareString1(str1, str2));
		System.out.println(compareString2(str2, str3));
	}
}
