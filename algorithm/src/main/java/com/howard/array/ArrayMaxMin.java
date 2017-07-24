package com.howard.array;
/**
 * 寻找数组中的最大值与最小值
 * 取双元素法
 * @author Howard
 * 2017年4月8日
 */
public class ArrayMaxMin {
	static int Max;
	static int Min;
	public static void GetMaxAndMin(int arr[]) {
		Max = arr[0];
		Min = arr[0];
		int len = arr.length;
		for (int i = 1; i < len - 1; i += 2) {
				if (i + 1 > len) {
					if (arr[i] > Max) 
						Max = arr[i];
					if (arr[i] < Min) 
						Min = arr[i];
				}else if (arr[i] > arr[i+1]) {
					if (arr[i] > Max) 
						Max = arr[i];
					if (arr[i+1] < Min) 
						Min = arr[i+1];
				}else if (arr[i] < arr[i+1]) {
					if (arr[i+1] > Max) 
						Max = arr[i+1];
					if (arr[i] < Min)
						Min = arr[i];
				}
		}
	}
	public static void main(String[] args) {
		int a[] = {10,8,40,2,1,10};
		GetMaxAndMin(a);
		System.out.println("Max="+Max);
		System.out.println("Min="+Min);
	}
}
