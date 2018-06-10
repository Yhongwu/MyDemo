package com.howard.string;

import java.util.Scanner;

/**
 * 字符串单词反转
 * 如how are you 反转后 you are how
 * 思路：先整体反转 uoy era woh此时只要对各个单词逐一反转一次就可以了
 * @author Howard
 * 2017年4月9日
 */
public class ReverseWord {
	private static void swap(char[] c,int begin,int end) {
		char tmp;
		while(begin < end) {
			tmp = c[begin];
			c[begin] = c[end];
			c[end] = tmp;
			begin++;
			end--;
		}
	}
	/**
	 * 反转单词字符串
	 * @param s
	 * @return
	 */
	public static String reverseWord(String s) {
		char[] c = s.toCharArray();
		//全部反转一次
		//System.out.println(c[0]);
		swap(c, 0, c.length - 1);
		int begin = 0;
		for (int i = 0; i < c.length; i ++) {
			//遇到单词反转
			if (c[i] ==' ') {
				swap(c, begin, i-1);
				//重新设置开始标志
				begin = i + 1;
			}
		}
		//对最后一个单词反转
		swap(c, begin, c.length - 1);
		return new String(c);
	}
	public static void main(String[] args) {
		//String s = "how are you";
		Scanner scanner = new Scanner(System.in);
		//字符串带空格输入 nextLine
		String str = scanner.nextLine();
		System.out.println(reverseWord(str));


	}
}
