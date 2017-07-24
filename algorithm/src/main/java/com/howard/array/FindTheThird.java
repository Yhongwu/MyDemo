package com.howard.array;
/**
 * 找出第三大数
 * 如果没有 则返回最大的
 * @author Howard
 * 2017年3月30日
 */
public class FindTheThird {
	public static void main(String[] args) {
		int array[] = {0,1,-2,9,10,5,13,8,1,15};
		int third = FindTheThird(array);
		System.out.println("第三大数为："+array[third]);
	}
	/**
	 * 找出第三大数的下标
	 * @param array 第三大数的下标
	 * @return
	 */
	public static int FindTheThird(int[] array) {
		//先将数组前三个进行排序 最多需要比较次数3次
		int temp = 0;
		if (array.length == 0) return -1;
		if (array.length == 1) return 0;
		if (array.length == 2) {
			if (array[0] < array[1]) {
				temp = array[0];
				array[0] = array[1];
				array[1] = temp;
			}
			return 0;
		}
		if (array[0] < array[1]) {
			temp = array[0];
			array[0] = array[1];
			array[1] = temp;
		}
		if (array[0] < array[2]) {
			temp = array[0];
			array[0] = array[2];
			array[2] = temp;
		}
		if (array[1] < array[2]) {
			temp = array[1];
			array[1] = array[2];
			array[2] = temp;
		}
		
		if (array.length == 3) return 0;
		
		int k = 0,m = 1,n =2;
		//剩下的n-3个数，分别进行3次比较3(n-3)
		for (int i = 3; i < array.length; i++) {
			if (array[i] > array[n]) {
				if(array[i] > array[m]) {
					if (array[i] > array[k]) {
						n = m;
						m = k;
						k = i;
					}else {
						n = m;
						m = i;
					}
				}else {
					n = i;
				}
			}
		}
		return n;
	}
}
