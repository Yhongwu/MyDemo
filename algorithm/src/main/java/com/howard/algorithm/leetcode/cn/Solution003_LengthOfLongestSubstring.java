package com.howard.algorithm.leetcode.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 无重复字符的最长子串是 "abc"，其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 无重复字符的最长子串是 "b"，其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 无重复字符的最长子串是 "wke"，其长度为 3。
 * 请注意，答案必须是一个子串，"pwke" 是一个子序列 而不是子串。
 * Created by yaohongwu on 2018/11/13.
 */
public class Solution003_LengthOfLongestSubstring {

    /**
     * 自解
     * O(n^2) ?
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        int k = 0;
        int max = 0;
        for (int i = 0; i < chars.length; i ++ ) {
            if (set.contains(chars[i])) {
                if (k >= max) {
                    max = k;
                }
                // 从上一子串的第二个字符开始重新找起
                i = i - k + 1;
                k = 1;
                set.clear();
            } else {
                k ++;
            }
            set.add(chars[i]);
        }
        // 最后再比较一次 防止从第一个字母开始就是最长子串的开头 不走 if (set.contains(chars[i])) 的判断
        if (k >= max) {
            max = k;
        }
        return max;
    }

    /**
     * 滑动窗口法
     * 时间复杂度：O(2n)=O(n) 在最糟糕的情况下，每个字符将被 i 和 j 访问两次。
     * 空间复杂度：O(min(m,n))。滑动窗口法需要 O(k) 的空间，其中 k 表示 Set 的大小。
     * 而Set的大小取决于字符串 s 的大小以及字符集/字母 m 的大小。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringII(String s) {
        Set<Character> set = new HashSet<>();
        int i = 0;
        int j = 0;
        int result = 0;
        int length = s.length();
        // 利用set形成滑动窗口
        while (i < length && j < length) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j ++ ;
                result = Math.max(result, j - i);
            } else {
                set.remove(s.charAt(i));
                i ++ ;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // bbbbb
        // abcabcbb
        // pwwkew
        // ""
        // " "
        // dvdf
        String str = "bbbbb";
        int result = new Solution003_LengthOfLongestSubstring().lengthOfLongestSubstring(str);
        System.out.println(result);
        int resultII = new Solution003_LengthOfLongestSubstring().lengthOfLongestSubstringII(str);
        System.out.println(resultII);
    }
}
