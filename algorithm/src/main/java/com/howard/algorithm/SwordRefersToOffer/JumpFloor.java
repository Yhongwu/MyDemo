package com.howard.algorithm.SwordRefersToOffer;
/**
 * 剑指offer
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 类似斐波那契数列
 * 不同的只是初始值
 * f(n) = f(n-1) + f(n-2)  f(1) = 1 f(2) = 2
 * 2017年9月16日
 * @author hongwu
 */
public class JumpFloor {
    public static int JumpFloor(int target) {
    	if (target == 0 || target == 1 || target == 2) {
    		return target;
    	}
    	int t1 = 1;
		int t2 = 2;
		int tn = 0;
    	for (int i = 3; i <= target ; i ++ ) {
    		tn = t1 + t2;
			t1 = t2;
			t2 = tn;
    	}
		return tn;
    }
    public static void main(String[] args) {
    	System.out.println(JumpFloor(4));
	}
}
