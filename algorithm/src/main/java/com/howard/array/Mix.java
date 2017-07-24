package com.howard.array;

import java.util.ArrayList;
import java.util.List;
/**
 * 计算两个有序数组的交集
 * 假设两个数组是有序的 （升序），求他们的交集
 * @author Howard
 * 2017年4月8日
 */
public class Mix {
	/**
	 * 二路归并法：
	 * 两个数组分别以i，j索引从头开始遍历，遍历过程进行比较
	 * 如果a1[i] == a2[j],则说明出现交集，加入集合
	 * 如果a1[i] > a2[j],则向后遍历a2，
	 * 如果a1[i] < a2[j],则向后遍历a1，
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static List<Integer> mixed(int a1[],int a2[]) {
		List<Integer> list = new ArrayList<>();
		int i = 0;
		int j = 0;
		while (i < a1.length && j < a2.length) {
			if (a1[i] == a2[j]) {
				list.add(a1[i]);
				i++;
				j++;
			}else if(a1[i] > a2[j]) {
				j++;
			}else if (a1[i] < a2[j]) {
				i++;
			}
		}
		return list;
	}
	public static void main(String[] args) {
		int a1[] = {1,2,3,4,5};
		int a2[] = {2,4,6,8};
		List<Integer> list = mixed(a1, a2);
		for (int i:list) {
			System.out.print(i+" ");
		}
		
	}
}
