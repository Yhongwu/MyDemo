package com.howard.array;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 找出数组中重复元素最多的数
 * @author Howard
 * 2017年4月8日
 */
public class FindMostFrequentInArray {
	/**
	 * 使用map映射表
	 * @param arr
	 * @return
	 */
	public static int findMostFrequentInArray(int arr[]) {
		int result = 0;
		int len = arr.length;
		//数组内容为空 不存在
		if (len == 0) {
			return Integer.MIN_VALUE;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < len; i++ ) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i])+1);
			}else {
				map.put(arr[i], 1);
			}
		}
		
		int most = 0;
		Iterator<Entry<Integer, Integer>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Integer> next = iterator.next();
			Integer key = next.getKey();
			Integer value = next.getValue();
			if (value > most) {
				result = key;
				most = value;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int a[] = {1,5,4,3,4,5,5,5,5,3,4,6,7,6,6,6,3,};
		System.out.println(findMostFrequentInArray(a));
	}
}
