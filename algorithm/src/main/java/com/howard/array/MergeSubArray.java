package com.howard.array;
/**
 * 对数组的两个子有序段进行合并
 * 问题：数组a[0,min-1]和a[min,n-1]是各自有序的，对数组的两个子有序段进行合并，
 * 得到整个有序的数组 要求空间复杂度为O(1),假定子有序数组都是升序的
 * 考虑到归并但是空间复杂度不符号要求
 * 可以使用插入排序，时间复杂度为O(n^2)，空间复杂度为O(1)，
 * 但是这个算法并没有用到子数组有序这个条件
 * @author Howard
 * 2017年4月8日
 */
public class MergeSubArray {
	/**
	 * 遍历数组下标0~min-1，如果发现a[mid]<a[i].那么交换这两个的值，然后用
	 * a[mid]在a[mid~n]中进行插入排序
	 * @param a
	 * @param mid
	 */
	public static void sort(int a[],int mid) {
		int tmp ;
		for (int i = 0; i <= mid - 1; i++) {
			if (a[mid] < a[i]) {
				tmp = a[i];
				a[i] = a[mid];
				a[mid] = tmp;
				for (int j = mid; j < a.length - 1; j++) {
					if (a[j+1] < a[j]) {
						tmp = a[j];
						a[j] = a[j+1];
						a[j+1] = tmp;
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		int a[] = {1,5,6,7,9,2,4,8,12,13,16};
		sort(a, 5);
		for (int i:a) {
			System.out.print(i+" ");
		}
	}
}	
