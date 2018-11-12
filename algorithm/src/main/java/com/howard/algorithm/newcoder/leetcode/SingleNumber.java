package com.howard.algorithm.newcoder.leetcode;

/**
 *  复杂度
 *  Given an array of integers, every element appears twice except for one. Find that single one.
 *  Note:
 *  Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumber {
    public static void main(String[] args) {
        int A[] = {1,1,2,2,3,3,4,4,5,6,6};
        int result = singleNumber(A);
        System.out.println(result);
    }
    public static int singleNumber(int[] A) {
        int result = 0;
        for (int i = 0 ; i < A.length; i ++) {
            result ^= A[i];
        }
        return result;
    }
}
