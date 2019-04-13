package com.howard.algorithm.leetcode.cn;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * Created by Howard Yao on 2019/3/30.
 */
public class Solution033_Search {
    /**
     * O(n)
     * 先找出最大的数 再判断target在哪边 然后二分
     * ps:O(n)直接遍历一次更粗暴简洁。。
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        // 边界
        if (nums.length <= 0) {
            return -1;
        }
        int max = 0;
        for (int i = 1; i < nums.length; i ++ ) {
            if (nums[max] < nums[i]) {
                max = i;
            }
        }
        int start = 0;
        int end = 0;
        if (target >= nums[0]) {
            start = 0;
            // 考虑数组只有升序情况下 target刚好为最大的那个数
            end = max;
        } else {
            start = max + 1;
            end = nums.length - 1;
        }

        while (start <= end) {
            // 注意移位运算符的等级低于加减号 需要加括号
            int mid = start + ((end - start) >> 1);
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     * O(logn)
     * 每得到一个中间数mid，会将数组分成一个有序和一个无序
     * 再根据target在有序和无序部分来调整start和end
     * @param nums
     * @param target
     * @return
     */
    public static int searchII(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (target == nums[mid]) {
                return mid;
            } else if (nums[mid] >= nums[start]) {
                // nums[mid] >= nums[start] 考虑中间数刚好是第一个数的情况
                // 左边有序 右边无序
                if (target >= nums[start] && target < nums[mid]) {
                    // target在左边
                    end = mid - 1;
                } else {
                    // target在右边
                    start = mid + 1;
                }
            } else {
                // 左边无序 右边有序
                if (target > nums[mid] && target <= nums[end]) {
                    // target在右边
                    start = mid + 1;
                } else {
                    // target在左边
                    end = mid - 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        // int[] nums = {4,5,6,7,0,1,2};
        // int[] nums = {0,1,2,4,5,6,7};
        int[] nums = {3, 1};
        // int[] nums = {};
        // int target = 0;
        // int target = 8;
        int target = 1;
        System.out.println(search(nums, target));
        System.out.println(searchII(nums, target));
    }
}
