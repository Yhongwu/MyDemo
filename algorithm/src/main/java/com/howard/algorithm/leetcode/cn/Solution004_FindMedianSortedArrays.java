package com.howard.algorithm.leetcode.cn;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * 你可以假设 nums1 和 nums2 不同时为空。
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 中位数是 2.0
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 中位数是 (2 + 3)/2 = 2.5
 * Created by yaohongwu on 2018/11/16.
 */
public class Solution004_FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] num = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i <  m && j < n) {
            if (nums1[i] < nums2[j]) {
                num[k ++ ] = nums1[i++];
            } else {
                num[k ++ ] = nums2[j++];
            }
        }
        while (i < m) {
            num[k ++ ] = nums1[i++];
        }
        while (j < n) {
            num[k ++ ] = nums2[j++];
        }
        double mid;
        if (num.length % 2 != 0) {
            mid = num[num.length / 2];
        }else {
            mid = (num[num.length / 2] + num[num.length / 2 - 1])/2.0;
        }
        return mid;
    }

    public static void main(String[] args) {
        // 1,2   3,4  2.5
        // 1,3   2    2.0
        int[] num1 = new int[]{1,3};
        int[] num2 = new int[]{2};
        double mid = new Solution004_FindMedianSortedArrays().findMedianSortedArrays(num1, num2);
        System.out.println(mid);
    }
}
