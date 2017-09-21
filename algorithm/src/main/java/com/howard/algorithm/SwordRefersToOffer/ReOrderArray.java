package com.howard.algorithm.SwordRefersToOffer;
/**
 * 剑指offer
 * 调整数组顺序使奇数位于偶数前面
 * 
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * 思路：1、类似插入排序，遇到偶数后面插入，奇数前面插入
 * 2、遍历，第一个奇数和倒数第一个偶数交换位置，以此类推
 * 3、空间换时间,用一个同样大小数组，先遍历保存奇数，再遍历保存偶数，最后复制回原数组
 *   时间复杂度O(n)+O(n)+O(n)=O(n)
 * 2017年9月18日
 * @author hongwu
 */
public class ReOrderArray {
    public static void reOrderArray(int [] array) {
        int[] a = new int[array.length];
        int j = 0;
        for (int i = 0 ; i < a.length; i ++) {
        	if (array[i] % 2 == 1) {
        		a[j++] = array[i];
        	}
        }
        for (int i = 0 ; i < a.length; i ++) {
        	if (array[i] % 2 == 0) {
        		a[j++] = array[i];
        	}
        }
        for (int i = 0 ; i < a.length; i ++ ) {
        	array[i] = a[i];
        }
    }
    
    public static void main(String[] args) {
		int[] array = {2,3,7,8,4,5,6};
		reOrderArray(array);
		for (int i : array) {
			System.out.println(i);
		}
	}
}
