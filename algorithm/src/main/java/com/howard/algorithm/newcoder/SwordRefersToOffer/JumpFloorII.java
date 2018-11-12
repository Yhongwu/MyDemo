package com.howard.algorithm.newcoder.SwordRefersToOffer;
/**
 * 剑指offer
 * 
 * 变态条台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法
 * 思路：找规律，先试着列出1~5，发现1有1种，2有2种，3有4种，4有8种，5有16种，呈指数增长
 * 2017年9月17日
 * @author hongwu
 */
public class JumpFloorII {
    public static int jumpFloorII(int target) {
    	if (target == 0 || target == 1) {
    		return target;
    	}
    	int result = 1;
    	for (int i = 1 ; i < target; i++) {
    		result *= 2;
    	}
		return result;
        
    }
	public static void main(String[] args) {
		System.out.println(jumpFloorII(0));;
	}
}
