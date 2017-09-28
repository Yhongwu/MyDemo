package com.howard.algorithm.SwordRefersToOffer;
/**
 * 剑指offer
 * 构建乘积数组 
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 * 
 * 思路：
 * 构建一个二维数组：
 *   1  2  3  4  5
 * 1 -
 * 2    -
 * 3       -
 * 4          -
 * 5             -
 * 则B[i]等于-左边的乘积乘以-右边的乘积
 * 所以分两步，第一步循环计算左边三角形
 * 第二步循环计算右边倒三角形
 * 再合并即可
 * 2017年9月24日
 * @author hongwu
 */
public class Multiply {
    public static int[] multiply(int[] A) {
    	int[] B = new int[A.length];
    	for (int i = 0 ; i < B.length; i ++) {
    		B[i] = 1;
    	}
    	int tmp = 1;
    	for (int i = 0 ; i < A.length - 1; i ++ ) {
    		tmp *= A[i];
    		B[i+1] = tmp;
    	}
    	tmp = 1;
    	for (int i = A.length - 1; i > 0; i --) {
    		tmp *= A[i];
    		B[i-1] = B[i-1] * tmp;
    	}
		return B;
    }
    public static void main(String[] args) {
    	int[] A = {1,2,3,4,5};
    	int[] B = multiply(A);
    	for (int i: B) {
    		System.out.print(i+" ");
    	}
	}
}
