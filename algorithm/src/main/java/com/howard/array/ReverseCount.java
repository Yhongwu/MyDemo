package com.howard.array;
/**
 * 求解数组中反序对的个数
 * 反序对，就是a[i]>a[j].如5，3，2，6，则(5,3),(3,2),(5,2)都是反序对
 * @author Howard
 * 2017年4月9日
 */
public class ReverseCount {
	public static int reverseCount = 0;
	/**
	 * 方法1：
	 * 蛮力法
	 * 时间复杂度O(n^2)
	 * @param a
	 * @return
	 */
	public static int reverseCount(int a[]) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j])
					count ++;
			}
		}
		return count;
	}
	
	/**
	 * 方法2：
	 * 利用归并排序(升序)
	 * 比如[5,8],[3,6],归并 第一步，3，前面的5和8都比它大，加2，第二步，5，前面没有数，加0，
	 * 第三步：6，只有8比6大，加1，第四步：8，没有比8大的 加
	 * 算法时间复杂度O(nlogn)
	 * @param nums 数组
	 * @param low 
	 * @param high
	 */
	public static void mergeSort(int[] nums,int low,int high){
		if(low < high){
			int middle = (low + high)/2; //中间点
			mergeSort(nums, low, middle); //对左边数组递归
			mergeSort(nums, middle + 1, high); //对右边数组递归
			Merge(nums, low, middle, high); //将左右已排好序的数组合并成有序数组
		}
	}
	public static void Merge(int[] nums,int low,int middle,int high){
		int[] temp = new int[nums.length];
		int mid = middle + 1; //右边数组第一个数的索引
		int k = low; 
		int t = low; //用于最后复制临时数组的值覆盖原数组的索引
		while(low <= middle && mid <= high){ //有序合并两个数组
			if(nums[low] <= nums[mid]){ 
				temp[k++] = nums[low++];
			}else{  
				//比归并排序仅添加这一步计数
				reverseCount = mid - low + 1;
				temp[k++] = nums[mid++]; //注意这里是mid不是high
			}
		}
		//剩余部分依次进入临时数组 
		while(low <= middle){
			temp[k++] = nums[low++];
		}
		while(mid <= high){
			temp[k++] = nums[mid++];
		}
		
		//复制回原数组 覆盖原来的顺序
		while(t <= high){
			nums[t] = temp[t++];
		}
	}
	
	public static void main(String[] args) {
		int a[] = {1,5,3,2,6};
		//测试时记得方法一先测 方法二会改变排序
		System.out.println(reverseCount(a));
		mergeSort(a, 0, a.length - 1);
		System.out.println(reverseCount);
	}
}
