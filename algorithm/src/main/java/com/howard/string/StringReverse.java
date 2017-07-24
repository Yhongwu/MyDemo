package com.howard.string;
/**
 * 用递归的方式反转字符串
 * @author Howard
 * 2017年2月12日
 */
public class StringReverse {

	public static void main(String[] args) {
		String s = "abcdefg";
		//递归
		System.out.println(reverse(s));
		//使用StringBuffer自带方法反转
		StringBuffer ss = new StringBuffer(s);
		StringBuffer sss = ss.reverse();
		System.out.println(sss.toString());
	}
	public static String reverse(String origl){
		if(origl == null || origl.length()<=1){
			return origl;
		}
		return reverse(origl.substring(1))+origl.charAt(0);
	}
}
