package com.howard.string;

import java.util.HashMap;
import java.util.Map;
/**
 * 求字符串里第一个出现次数最少的字符
 * cvte面试题
 * 思路：使用hashmap
 * 2017年8月3日
 * @author hongwu
 */
public class FindFirstMostStr {
	public static void getResult(String str) {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i ++) {
			if (!map.containsKey(str.substring(i, i+1))) {
				map.put(str.substring(i,i+1), 1);
			}else {
				map.put(str.substring(i, i+1),map.get(str.substring(i, i+1)) + 1);
			}
		}
		//打印查看map里的结果
//		for (Map.Entry<String, Integer> entry : map.entrySet()) {  
//		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
//		} 
		//使用str来遍历map，可以成功查找第一个出现最小次数的字符
		int min = Integer.MAX_VALUE;
		String minStr = null;
		for (int i = 0 ; i < str.length(); i ++ ) {
			if (min > map.get(str.substring(i, i+1))) {
				min = map.get(str.substring(i, i+1));
				minStr = str.substring(i, i+1);
			}
		}
		System.out.println(minStr);
	}
	public static void main(String[] args) {
		String str = "23412475362341";
		getResult(str);
	}
}
