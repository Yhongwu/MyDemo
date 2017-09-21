package com.howard.algorithm.test;
/**
 * 二进制中1的个数  未完成
 * 2017年9月17日
 * @author hongwu
 */
public class NumberOf1 {
    public static int numberOf1(int n) {
    	int count = 0;
    	if (n < 0) {
    		n = ~n;
    		System.out.println(n);
    		n += 1;
    		count ++ ;
    	}
    	while (n > 0) {
    		n = n & (n - 1);
    		count ++;
    	}
    	return count;
    }
    public static void main(String[] args) {
		System.out.println(numberOf1(-1));;
	}
}
