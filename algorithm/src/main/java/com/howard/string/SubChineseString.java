package com.howard.string;

import java.io.UnsupportedEncodingException;
/**
 * 为字符串截取n个字节 这里n指一个中文字符或者一个其它字符
 * 所以要避免出现中文字符被截取一半
 * byte<0的一般是中文字符
 * @author Howard
 * 2017年3月9日
 */
public class SubChineseString {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "我爱中华abc我爱中国def";
		int num = trimUTF(str.getBytes("UTF-8"),3);
		System.out.println(num);
		System.out.println(str.substring(0, num));
		
	}
	/**
	 * GBK
	 * 一个中文字符占2个字节
	 * @param buf
	 * @param n
	 * @return
	 */
	public static int trimGBK(byte[] buf,int n) {
		int num = 0;
		boolean isChaineseFirstHalf = false;
		for(int i = 0; i < n; i++) {
			if (buf[i] < 0 && !isChaineseFirstHalf) {
				isChaineseFirstHalf = true;
			}else {
				num++;
				isChaineseFirstHalf = false;
			}
		}
		return num;
	}
	/**
	 * utf-8
	 * 一个中文字符占三个字节
	 * 用t来辅助判断
	 * @param buf
	 * @param n
	 * @return
	 */
	public static int trimUTF(byte[] buf,int n) {
		int num = 0;
		int t = 0;
		boolean isChaineseFirstHalf = false;
		for(int i = 0; i < n; i++) {
			if (buf[i] < 0 && !isChaineseFirstHalf) {
				t++;
				if (t==2) isChaineseFirstHalf = true;
			}else {
				num++;
				t = 0;
				isChaineseFirstHalf = false;
			}
		}
		//如果最后t!=0 说明有中文字符截取了一半
		if(t != 0) num++;
		return num;
	}
}
