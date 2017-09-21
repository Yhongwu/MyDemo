package com.howard.algorithm.NetEase;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 字符串碎片
 * 网易2018校招编程题
 * 
 * 一个由小写字母组成的字符串可以看成一些同一字母的最大碎片组成的。例如,"aaabbaaac"是由下面碎片组成的:'aaa','bb','c'。
 * 牛牛现在给定一个字符串,请你帮助计算这个字符串的所有碎片的平均长度是多少。
 * 输入描述：输入包括一个字符串s,字符串s的长度length(1 ≤ length ≤ 50),s只含小写字母('a'-'z')
 * 输出描述：输出一个整数,表示所有碎片的平均长度,四舍五入保留两位小数。
 * 		     如样例所示: s = "aaabbaaac"
 *        所有碎片的平均长度 = (3 + 2 + 3 + 1) / 4 = 2.25
 * 输入：aaabbaaac 输出 2.25
 * 2017年9月14日
 * @author hongwu
 */
public class StringFragmentation {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		sc.close();
		int fragment = 0;
		int sum = 0;
		int count = 0;
		for (int i = 0 ; i < str.length(); i ++ ) {
			if (i == 0 || !str.substring(i, i+1).equals(str.substring(i-1,i))) {
				sum += fragment;
				count ++ ;
				fragment = 1;
			}else {
				fragment ++ ;
			}
		}
		sum += fragment;
		
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00");  
		String result=fnum.format(sum/(count*1.0));      
		System.out.println(result);
	}
}
