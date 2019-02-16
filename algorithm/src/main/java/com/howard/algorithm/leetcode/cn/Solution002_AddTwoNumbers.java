package com.howard.algorithm.leetcode.cn;

import com.howard.algorithm.leetcode.cn.utils.ListNode;

/**
 * 两数相加
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * Created by yaohongwu on 2018/11/12.
 */
public class Solution002_AddTwoNumbers {
    /**
     * m,n为l1和l2长度
     * 时间复杂度：O(max(m,n))
     * 空间复杂度：O(max(m,n)) 新列表的长度最多为 max(m,n)+1\
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode listNode = null;
        int jinwei = 0;
        while (l1 != null || l2 != null) {
            int i = l1 == null? 0: l1.val;
            int j = l2 == null? 0: l2.val;
            int tmp =  i + j + jinwei;
            if (tmp >= 10) {
                tmp = tmp - 10;
                jinwei = 1;
            } else {
                jinwei = 0;
            }
            if (listNode != null) {
                ListNode node = new ListNode(tmp);
                listNode.next = node;
                listNode = node;
            } else {
                listNode = new ListNode(tmp);
                head = listNode;
            }
            l1 = l1 == null? null : l1.next;
            l2 = l2 == null? null : l2.next;
        }
        if (jinwei > 0) {
            ListNode node = new ListNode(jinwei);
            listNode.next = node;
        }
        return head;
    }

    public static void main(String[] args) {
        // 243 564 708   342+465=807
        // 24  564 705  42+465=507
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode r1 = new ListNode(5);
        ListNode r2 = new ListNode(6);
        ListNode r3 = new ListNode(4);
        r1.next = r2;
        r2.next = r3;

        ListNode listNode = new Solution002_AddTwoNumbers().addTwoNumbers(l1, r1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    /*class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }*/
}
