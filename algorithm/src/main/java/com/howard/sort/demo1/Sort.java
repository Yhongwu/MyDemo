package com.howard.sort.demo1;

import java.util.ArrayList;
import java.util.List;
/**
 * 8种排序算法的java实现
 * 直接插入 希尔 选择 堆排序 冒泡 快速 归并 基数
 * @author Howard
 * 2017年2月25日
 */
public class Sort {
	
	public static void main(String[] args) {
		int[] nums = {23,45,67,12,97,4,32,55};
//		bubbleSort(nums);
//		quickSort(nums);
		insertSort(nums);
//		shellSort(nums);
//		selectSort(nums);
//		heapSort(nums);
//		mergeSort(nums, 0, nums.length-1);
//		radixSort(nums);
		for(int i = 0;i<nums.length;i++){
			System.out.print(nums[i]+" ");
		}
		
		
	}
	/**
	 * 冒泡排序(升序)
	 * 稳定
	 * @param nums
	 */
	public static void bubbleSort(int[] nums){
		
		for(int i = 0;i<nums.length;i++)
			for(int j = 0;j<nums.length-i-1;j++){
				if(nums[j]>nums[j+1]){
					int tmp = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = tmp;
				}
			}
		
	}
	/**
	 * 快速排序 分治法
	 * 以下三个方法用于快速排序
	 * 不稳定
	 * @param nums
	 */
	public static void quickSort(int[] nums){
		_quickSort(nums, 0, nums.length-1);
	}
	
	public static void _quickSort(int[] nums,int low,int high){
		//这步判断必须加
		if(low < high){
			int middle = getMiddle(nums,low,high);
			_quickSort(nums, low, middle-1);
			_quickSort(nums, middle+1, high);
		}
	}
	public static int getMiddle(int[] nums,int low,int high){
		int tmp = nums[low];
		while(low < high){
			while (nums[high] >= tmp && low < high) {
				high--;
			}
			nums[low] = nums[high];
			while (nums[low] <= tmp && low < high) {
				low++;
			}
			nums[high] = nums[low];
		}
		nums[low] = tmp;
		return low;
	}
	/**
	 * 直接插入排序(升序)
	 * 稳定
	 * @param nums
	 */
	public static void insertSort(int[] nums){
		for(int i = 1;i < nums.length;i++){
			int tmp = nums[i];
			int j = i - 1;
			for(;j >= 0 && tmp < nums[j];j--){
				nums[j+1] = nums[j]; //不满足 则后面的逐渐往前移位
			}
			nums[j+1] = tmp;
		}
	}
	/**
	 * 希尔排序
	 * 不稳定
	 * @param nums
	 */
	public static void shellSort(int[] nums){
		int d = nums.length;
		while(d > 1){
			d = (d+1)/2;
			for(int z = 0; z < d; z++){
				for(int i = z + d;i < nums.length; i+=d ){
					int tmp = nums[i];
					int j = i - d;
					for(; j >= 0 && tmp < nums[j]; j-=d){
						nums[j+d] = nums[j];
					}
					nums[j+d] = tmp;
				}
			}
		}
	}
	/**
	 * 选择排序(升序)
	 * 不稳定
	 * @param nums
	 */
	public static void selectSort(int[] nums){
		/*for(int i = 0;i < nums.length;i++){
			for(int j = i+1; j < nums.length;j++){
				if(nums[i] > nums[j]){
					int tmp = nums[i];
					nums[i] = nums[j];
					nums[j] = tmp;
				}
			}
		}*/
		
		/**另一种方式 比上面更佳*/
		int k = 0;
		int temp = 0;
		for(int i = 0;i < nums.length;i++){
			k = i;
			for(int j = i+1; j < nums.length;j++){
				if(nums[k] > nums[j]){
					k = j;
				}
			}
			if (k != i) {
				temp = nums[k];
				nums[k] = nums[i];
				nums[i] = temp;
			}
		}
			
	}
	/**
	 * 堆排序(升序)
	 * 不稳定
	 * 下面三个方法
	 * @param nums
	 */
	public static void heapSort(int[] nums){
		int size = nums.length;
		for(int i = 0;i < size-1;i++){
			buildMaxHeap(nums,size-1-i); //建立大顶堆
			Swap(nums, 0, size-i-1); //交换第一个即最大的和堆的最后一个数
		}
	}
	
	public static void buildMaxHeap(int[] nums,int last){
		//注意从最后一个节点的父节点开始建立堆
		for(int  i = (last-1)/2;i >= 0;i--){
			int k = i;
			//while(k*2+1 <= last){
				//数组 起始点为0 这里bigger为子节点的左节点
				int bigger = 2*k+1;
				if (bigger <= last) { //左节点存在
					if (bigger+1 <= last) { //右节点存在
						if (nums[bigger] < nums[bigger+1]) {
							bigger++; //bigger指向子节点较大的那个索引
						}
					}
				}
				//当前节点即上面说的子节点的父节点与子节点中最大的进行比较 
				if (nums[k] < nums[bigger]) {
					//如果小 就交换
					Swap(nums,k,bigger);
				}
				//break; //while其实这里可不写 相应的break可以去掉
			//}
		}
	}
	/**
	 * 交换nums[m]和nums[n]
	 * @param nums
	 * @param m
	 * @param n
	 */
	public static void Swap(int[] nums,int m,int n){
		int tmp = nums[m];
		nums[m] = nums[n];
		nums[n] = tmp;
	}
	/**
	 * 归并排序(升序)
	 * 稳定
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
	/**
	 * 基数排序
	 * @param nums
	 */
	public static void radixSort(int[] nums){
		//获取数组中最大的数
		int max = nums[0];
		for(int i = 0;i < nums.length;i++){
			if(max < nums[i]){
				max = nums[i];
			}
		}
		//通过最大的数得到最大位数，确定排序次数
		int t = 0;
		while(max > 0){
			max/=10;
			t++;
		}
		//初始化链表 每一个数又可能有多个值 所以list里的元素仍然为链表这里即数组集合
		List<ArrayList<Integer>> list = new ArrayList<>();
		for(int i = 0; i < 10;i++){
			ArrayList<Integer> alist = new ArrayList<>();
			list.add(alist);
		}
		//开始排序
		for(int i = 0; i < t;i++){
			for(int j = 0;j < nums.length;j++){
				//得到第i位数
				int n = nums[j]%(int)Math.pow(10, i+1)/(int)Math.pow(10, i);
				ArrayList<Integer> arrayList = list.get(n);
				arrayList.add(nums[j]);
				list.set(n, arrayList);
			}
			//按第i位数的大小顺序收集元素 如果有相同的 按在数组里的排序
			int count = 0; //数组索引
			for(int y = 0; y < 10;y++){
				while(list.get(y).size() > 0){
					ArrayList<Integer> alist = list.get(y);
					nums[count] = alist.get(0);
					//为保证取出数的顺序以及判断是否取完 这里remove掉取出的数据 同时为下一趟排序清空数据
					alist.remove(0); 
					count++;
				}
			}
		}
		
		
	}
}
