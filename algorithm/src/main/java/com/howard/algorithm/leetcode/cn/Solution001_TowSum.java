package com.howard.algorithm.leetcode.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * Created by yaohongwu on 2018/11/12.
 */
public class Solution001_TowSum {
    /**
     * 暴力
     * time:O(n^2)
     * space: O(1)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0 ; i < nums.length; i ++ ) {
            for (int j = i + 1; j < nums.length; j ++ ) {
                if (nums[j] == target - nums[i]) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 两次哈希法
     * time: O(n)
     * space: O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumII(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++ ) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i ++ ) {
            Integer j = map.get(target - nums[i]);
            if (null != j && j != i) {
                result[0] = i;
                result[1] = j;
                break;
            }
        }
        return result;
    }

    /**
     * 一次哈希法
     * time: O(n)
     * space: O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumIII(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++ ) {
            Integer n = target - nums[i];
            // 边遍历边判断是否存在
            if (map.containsKey(n)) {
                // !!
                result[0] = map.get(n);
                result[1] = i;
                break;
            }
            map.put(nums[i], i);
        }

        return result;
    }
    public static void main(String[] args) {
        //int[] nums = new int[]{};
        //int target = 9;
        // 2,7,11,15  9   [0,1]
        // 3,2,4      6   [1,2]
        // 0,4,3,0    0   [0,3]
        // -1,-2,-3,-4,-5  -8  [2,4]
        int[] nums = new int[]{0,4,3,0};
        int target = 0;

        int[] resultI = new Solution001_TowSum().twoSum(nums, target);
        System.out.println(Arrays.toString(resultI));
        int[] resultII = new Solution001_TowSum().twoSumII(nums, target);
        System.out.println(Arrays.toString(resultII));
        int[] resultIII = new Solution001_TowSum().twoSumIII(nums, target);
        System.out.println(Arrays.toString(resultIII));
    }


}
