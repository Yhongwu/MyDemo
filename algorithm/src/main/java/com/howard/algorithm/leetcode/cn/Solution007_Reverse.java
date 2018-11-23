package com.howard.algorithm.leetcode.cn;

/**
 * 整数反转
 *
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * Created by yaohongwu on 2018/11/20.
 */
public class Solution007_Reverse {
    public int reverse(int x) {
        String str = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (str.charAt(0) == '-') {
            sb.append("-");
            i = 1;
        }
        for (int j = str.length() - 1; j >= i; j -- ) {
            sb.append(str.charAt(j));
        }
        Long num = Long.valueOf(sb.toString());
        if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
            return 0;
        }
        return num.intValue();
    }

    public static void main(String[] args) {
        int x = -2147483648;
        int reverse = new Solution007_Reverse().reverse(x);
        System.out.println(reverse);
    }
}
