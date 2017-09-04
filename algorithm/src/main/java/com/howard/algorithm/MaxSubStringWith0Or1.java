package com.howard.algorithm;

import java.util.Scanner;
/**
 * [编程题] 交错01串
 * 时间限制：1秒
 * 空间限制：32768K
 * 如果一个01串任意两个相邻位置的字符都是不一样的,我们就叫这个01串为交错01串。例如: "1","10101","0101010"都是交错01串。
 * 小易现在有一个01串s,小易想找出一个最长的连续子串,并且这个子串是一个交错01串。小易需要你帮帮忙求出最长的这样的子串的长度是多少。 
 * 输入描述：输入包括字符串s,s的长度length(1 ≤ length ≤ 50),字符串中只包含'0'和'1'
 * 输出描述:输出一个整数,表示最长的满足要求的子串长度。
 * 输入例子：111101111
 * 输出例子：3
 * 2017年8月15日
 * @author howard
 */
public class MaxSubStringWith0Or1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        in.close();
        //String s = "1000001101";
        int max = 0;
        int tmp = 1;
        for (int i = 1 ; i < s.length(); i ++ ) {
        	if (s.substring(i, i+1).equals(s.substring(i-1,i))) {
        		if (max < tmp) {
        			max = tmp;
        		}
        		tmp = 1;
        	}else {
        		tmp ++ ;
        		if (i == (s.length() - 1)) {
        			if (max < tmp) {
            			max = tmp;
            		}
        		}
        	}
        }
        System.out.println(max);
    }
}
