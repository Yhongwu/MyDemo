package com.howard.algorithm.SwordRefersToOffer;
/**
 * 剑指offer
 * 二叉搜索树的后序遍历序列 
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 * 思路：后序遍历的一个特点就是最后一个为根，又因为题目说的是二叉搜索树，所以root前面的
 * 序列中，分为2部分，左半部比root小，右半部比root大，
 * 这里可以采取一个方法，遍历一次找到第一个比root大的，则在这个数左边正常情况应该比root小，右边(包含找到的这个数)应该都比root大
 * 只要不满足这个条件，就直接返回false。
 * 如果满足，继续分别对最左边和右边进行判断。
 * 本题可以采用递归，也可以非递归实现
 * 2017年9月28日
 * @author hongwu
 */
public class VerifySquenceOfBST {
	/**
	 * 非递归版本
	 * @param sequence
	 * @return
	 */
    public static boolean verifySquenceOfBST(int [] sequence) {
        return isSearchTree(sequence, sequence.length-1);
    }
    public static boolean isSearchTree(int [] sequence,int length) {
    	if (length <= 0) {
    		return false;
    	}
    	while (length > 0) {
    		int root = sequence[length];
    		int index = 0;
    		for ( ; index < length - 1; index ++ ) {
            	if (sequence[index] > root) break;
            }
    		for (int i = 0 ; i < index - 1; i ++) {
            	if (sequence[i] > root) return false; 
            }
    		for (int i = index; i < length - 1; i ++ ) {
    	        if (sequence[i] < root) return false;
    	    }
    		length = length - 1;
    	}
        return true;
    }
    /**
     * 递归版本
     * 思路来自网上
     * @param sequence
     * @return
     */
    public static boolean verifySquenceOfBSTII(int [] sequence) {
    	if (sequence.length == 0) return false;
        return isSearchTree(sequence, sequence.length-1);
    }
    public static boolean isSearchTree(int [] sequence,int start,int end) {
    	if (end <= start) return true;
    	int index = start;
    	for (; index < end; index++) {
    		if (sequence[index] > sequence[end]) break;
    	}
    	for (int i = index; i < end; i++) {
    		if (sequence[i] < sequence[end]) return false;
    	}
    	return isSearchTree(sequence, start, index-1) && isSearchTree(sequence, index, end-1);
    }
    public static void main(String[] args) {
		int[] sequence = {2,4,3,8,14,12,10,5};
		int[] sequence2 = {};
		System.out.println(verifySquenceOfBST(sequence));
		System.out.println(verifySquenceOfBST(sequence2));
		System.out.println(verifySquenceOfBSTII(sequence));
		System.out.println(verifySquenceOfBSTII(sequence2));
	}
}
