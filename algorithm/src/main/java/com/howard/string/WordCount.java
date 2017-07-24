package com.howard.string;
/**
 * 统计字符串中单词数
 * @author Howard
 * 2017年4月9日
 */
public class WordCount {
	/**
	 * 思路：
	 * 若某个字符为不为空，而它前面的字符是空格，则说明新单词开始了
	 * 此时计数器加1，若当前字符非空格而前面字符也非空格，说明还是原来的单词
	 * @param s
	 * @return
	 */
	public static int wordCount(String s) {
		int word = 0;
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				word = 0;
			}else if (word == 0) {
				word = 1;
				count ++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		String s = "how are  you";
		System.out.println(wordCount(s));
	}
}
