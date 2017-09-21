package com.howard.algorithm.SwordRefersToOffer;
/**
 * 剑指offer
 * 二维数组中的查找
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 
 * 思路：抓住从左到右递增，从上到下递增的规律，可以从右上角的数a[i][j]开始查找，如果
 * a[i][j] > target,则a[i][j..]只会比target更大，所以应该j--，如果a[i][j] < target
 * 则i++,因为a[i..][j]只会更小
 * 2017年9月15日
 * @author hongwu
 */
public class FindInSortArray {
	public static void main(String[] args) {
//		int[][] array = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
		int[][] array = {{1,2,8,9},{4,7,10,13}};
		System.out.println(Find(7, array));
	}
	public static boolean Find(int target, int [][] array) {
		int hang = 0;
		int lie = array[0].length - 1;
		while (hang < array.length && lie >= 0) {
			System.out.println(hang+"--"+lie+"=="+array[hang][lie]);
			if (array[hang][lie] == target) {
				return true;
			}else if (array[hang][lie] < target) {
				hang ++ ;
			}else {
				lie  -- ;
			}
		}
		return false;
    }
}
