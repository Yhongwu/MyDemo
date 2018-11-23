package com.howard.algorithm.leetcode.cn;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * Created by yaohongwu on 2018/11/13.
 */
public class Solution005_LongestPalindrome {

    /**
     * 暴力法
     * 超时。。。。。。
     * O(n^3)
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int start = 0;
        int end = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i ++ ) {
            for (int j = i + 1; j < s.length(); j ++ ) {
                if (s.charAt(i) == s.charAt(j)) {
                    int m = i + 1;
                    int n = j - 1;
                    boolean flag = true;
                    while (m < n) {
                        if (s.charAt(m) != s.charAt(n)) {
                            flag = false;
                        }
                        m ++;
                        n --;
                    }
                    if (flag) {
                        if (max < (j - i)) {
                            max = j - i;
                            start = i;
                            end = j;
                        }
                    }
                }
            }
        }
        return s.substring(start,end + 1);
    }
    public String longestPalindromeII(String s) {
        //     c  b  b  c
        //  c  1  0  0  4
        //  b     1  2  0
        //  b        1  0
        //  c           1
        return null;
    }
    public static void main(String[] args) {
        // cbbd
        // babad aba
        // ""
        // a
        // ac
        String str = "babad";
        String s = new Solution005_LongestPalindrome().longestPalindrome(str);
        System.out.println(s);
    }
}
