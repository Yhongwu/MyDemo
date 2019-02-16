package com.howard.algorithm.leetcode.cn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:
 * 输入: 121
 * 输出: true
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * Created by yaohongwu on 2018/11/16.
 */
public class Solution009_IsPalindrome {

    /**
     * 利用队列
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        Queue<Character> queue = new LinkedList<>();
        String s = String.valueOf(x);
        for (int i = 0 ; i < s.length(); i ++ ) {
            queue.offer(s.charAt(i));
        }
        for (int i = s.length() - 1; i >= 0; i --) {
            if (queue.peek() == s.charAt(i)) {
                queue.poll();
            }else {
                return false;
            }
        }
        if (queue.isEmpty()) {
            return true;
        }
        return false;
    }
 /*   罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

    字符          数值
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000

    例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

    通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

    I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
    X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
    C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。

    给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。*/
    public static void main(String[] args) {
        int x = -121;
        boolean palindrome = new Solution009_IsPalindrome().isPalindrome(x);
        System.out.println(palindrome);
    }
}
