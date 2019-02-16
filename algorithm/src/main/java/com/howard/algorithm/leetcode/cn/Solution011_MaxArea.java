package com.howard.algorithm.leetcode.cn;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * Created by Howard Yao on 2018/11/19.
 */
public class Solution011_MaxArea {

    /**
     * 双指针法
     * 用一前一后两个指针 最大的面积由较小的决定
     * 此时将较小的进行向前(后）移动，因为如果移动较大的 之后的面积只会更小 所以移动较小的
     *
     * time：O(n)
     * space: O(1)
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
       int i = 0;
       int j = height.length - 1;
       int maxArea = 0;
       while (i < j) {
           if (height[i] > height[j]) {
               int tmp = height[j] * (j - i);
               if (tmp > maxArea) {
                   maxArea = tmp;
               }
               j--;
           }else {
               int tmp = height[i] * (j - i);
               if (tmp > maxArea) {
                   maxArea = tmp;
               }
               i++;
           }
       }
       return maxArea;
    }

    /**
     * 暴力法
     * time：O(n^2)
     * space: O(1)
     * @param height
     * @return
     */
    public int maxAreaII(int[] height) {
        int maxArea = 0;
        for (int i = 0 ; i < height.length; i ++ ) {
            for (int j = i + 1; j < height.length; j ++ ) {
                int tmp = height[i] < height[j]? height[i] * (j - i) : height[j] * (j -i);
                if (tmp > maxArea) {
                    maxArea = tmp;
                }
            }
        }
        return maxArea;
    }
    public static void main(String[] args) {
        // 1,8,6,2,5,4,8,3,7  49
        // 1,2                1
        int[] height = {1,8,6,2,5,4,8,3,7};
        int maxArea = new Solution011_MaxArea().maxArea(height);
        System.out.println(maxArea);
        int maxAreaII = new Solution011_MaxArea().maxAreaII(height);
        System.out.println(maxAreaII);
    }
}
