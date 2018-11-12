package com.howard.algorithm.newcoder.SwordRefersToOffer;
/**
 * 剑指offer
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * @author yaohongwu
 *
 */
public class RectCover {
	public static void main(String[] args) {
		System.out.println(rectCover(38));
	}
	public static int rectCover(int target) {
		if (target < 0) {
			return 0;
		}
		int array[] = {0,1,2};
		if (target >= 0 && target <= 2) {
			return array[target];
		}
		return rectCover(target - 1) + rectCover(target - 2);
	}
}
