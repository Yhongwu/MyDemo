package com.howard.array;
/**
 * 求最大子数组之和
 * 问题：一个有n个元素的数组，这n个元素可以是正数也可以是负数，
 * 数组中连续的一个或多个元素可以组成一个连续的子数组
 * 一个数组可能有多个这样的子数组，求子数组和最大值
 * @author Howard
 * 2017年4月8日
 */
public class MaxSubArray {
	/**
	 * 方法1：
	 * 蛮力法
	 * 时间复杂度O(n^3)
	 * @param arr
	 * @return
	 */
	public static int maxSubArray1(int arr[]) {
		int len = arr.length;
		int tempSum = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				tempSum = 0;
				for (int k = i; k <= j; k++) {
					tempSum += arr[k];
				}
				if (tempSum > maxSum) 
					maxSum = tempSum;
			}
		}
		return maxSum;
	}
	/**
	 * 方法2：
	 * 对方法1的优化，利用已经求出的和
	 * 时间复杂度 O(n^2)
	 * @param arr
	 * @return
	 */
	public static int maxSubArray2(int arr[]) {
		int len = arr.length;
		int tempSum = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			tempSum = 0;
			for (int j = i; j < len; j++) {
				tempSum += arr[j];
				if (tempSum > maxSum) 
					maxSum = tempSum;
			}
		}
		return maxSum;
	}
	/**
	 * 求两个数的最大值
	 * @param m
	 * @param n
	 * @return
	 */
	public static int max(int m,int n) {
		return m > n ? m:n;
	}
	/**
	 * 方法3：
	 * 动态规划法
	 * 首先根据数组的最后一个元素arr[n-1]与最大子数组的关系可以分为以下三种情况：
	 * 1）最大子数组包含arr[n-1],即以arr[n-1]结尾
	 * 2）arr[n-1] 单独构成最大子数组
	 * 3）最大子数组不包含arr[n-1],那么求arr[1...n-1]的最大子数组可以转换为求arr[1...n-2]的最大子数组
	 * 所以，假设已经计算出了(arr[0],...,arr[n-1])最大的一段数组和为all[i-1],同时也计算出了（arr[0],...,arr[i-1]）中包含
	 * arr[i-1]的最大一段数组和为end{i-1],则可以得出如下关系：
	 * all[i-1] = max{arr[i-1],end[i-1],arr[i-2]}
	 * 利用该思想可以解决
	 * 该算法时间复杂度为O(n),但是额外申请了两个数组空间，所以空间复杂度也为O(n)
	 * @param arr
	 * @return
	 */
	public static int maxSubArray3(int arr[]) {
		int len = arr.length;
		int end[] = new int[len];
		int all[] = new int[len];
		//end[len-1] = arr[len-1];
		//all[len-1] = arr[len-1];
		end[0] = all[0] = arr[0];
		for (int i = 1; i < len; i++) {
			end[i] = max(end[i-1] + arr[i],arr[i]);
			//保存获取到的最大值
			all[i] = max(end[i],all[i-1]);
		}
		return all[len-1];
	}
	/**
	 * 方法4：
	 * 优化的动态规划
	 * 用all和end代替all数组和end数组
	 * 降低空间复杂度
	 * @param arr
	 * @return
	 */
	public static int maxSubArray4(int arr[]) {
		int len = arr.length;
		int end = arr[0];
		int all = arr[0];
		for (int i = 1; i < len; i++) {
			end = max(end + arr[i],arr[i]);
			all = max(end,all);
		}
		return all;
	}
	
	static int begin = 0;
	static int end = 0;
	/**
	 * 方法5：
	 * 确定最大子数组的起始点和结束点
	 * 利用方法三，可以看出end[i] = (end[i-1]+arr[i],arr[i])仅当end[i-1] < 0 时，
	 * end[i] = arr[i],所以当end[i-1] < 0 时，起始点重新计算
	 * @param arr
	 * @return
	 */
	public static int maxSubArray5(int arr[]) {
		int maxSum = Integer.MIN_VALUE;
		int tempSum = 0;
		int start = 0;
		for (int i = 0; i < arr.length; i++) {
			if (tempSum < 0) {
				tempSum = arr[i];
				start = i;
			} else {
				tempSum += arr[i];
			}
			if (tempSum > maxSum ) {
				maxSum = tempSum;
				begin = start;
				end = i;
			}
			
		}
		return maxSum;
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = {1,-2,5,8,-4,7,-1,-4};
//		System.out.println(maxSubArray1(a));
//		System.out.println(maxSubArray2(a));
		System.out.println(maxSubArray3(a));
//		System.out.println(maxSubArray4(a));
//		System.out.println(maxSubArray5(a));
//		System.out.println("开始位置： "+begin+" 结束位置： "+end);
	}
}
