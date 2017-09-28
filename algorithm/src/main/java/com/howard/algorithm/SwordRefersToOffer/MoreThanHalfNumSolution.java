package com.howard.algorithm.SwordRefersToOffer;

import java.util.HashMap;
import java.util.Map;
/**
 * 剑指offer
 * 数组中出现次数超过一半的数字 
 * 2017年9月24日
 * @author hongwu
 */
public class MoreThanHalfNumSolution {
    public static int moreThanHalfNum_Solution(int [] array) {
    	if (array.length == 1) {
    		return array[0];
    	}
    	Map<Integer, Integer> map = new HashMap<>();
    	for (int i = 0 ; i < array.length; i ++ ) {
    		Integer key = map.get(array[i]);
    		if (key == null) {
    			map.put(array[i], 1);
    		}else {
    			map.put(array[i], key + 1);
    			if (key + 1 > array.length / 2) {
    				return array[i];
    			}
    		}
    	}
		return 0;
    }
    /**
     * 解法2：
     * 时间复杂度O(N)
     * @param array
     * @return
     */
    public static int moreThanHalfNum_SolutionII(int [] array) {
    	if (array.length == 1) {
    		return array[0];
    	}
    	//思路：每次同时删除2个不同的数 取最后留下的数 则结果那个数可能就是所要求的
    	//出现一次加1 出现一个不同的减1
    	int cand = array[0];
    	int count = 1;
    	for (int i = 1 ; i < array.length; i ++) {
    		if (cand == array[i]) count ++;
    		else count --;
    		if (count == 0) {
    			cand = array[i];
    			count = 1;
    		}
    	}
    	count = 0;
    	//验证最后选出来的是否真的超过一半
    	//比如出现1 2 3 选出的cand是3 不是最后要的
    	for (int i = 0 ; i < array.length; i ++ ) {
    		if (array[i] == cand) count ++;
    	}
    	if (count > array.length/2) return cand;
		return 0;
    }
    public static void main(String[] args) {
		int a[] = {1,2,3,2,2,2,5,4,2};
		System.out.println(moreThanHalfNum_SolutionII(a));
	}
}
