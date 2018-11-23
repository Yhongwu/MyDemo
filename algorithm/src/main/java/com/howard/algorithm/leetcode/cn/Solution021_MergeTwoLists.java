package com.howard.algorithm.leetcode.cn;

import com.howard.algorithm.leetcode.cn.utils.ListNode;

/**
 * 21. 合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * Created by yaohongwu on 2018/11/20.
 */
public class Solution021_MergeTwoLists {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode list = head;
        while (l1 != null && l2  != null) {
            ListNode tmp = null;
            if (l1.val > l2.val) {
                tmp = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                tmp = new ListNode(l1.val);
                l1 = l1.next;
            }
            if (list != null) {
                list.next = tmp;
            } else {
                head = tmp;
            }
            list = tmp;
        }
        if (l1 != null) {
            // 如果一开始输入其中一个链表就为空的情况下
            if (head == null) {
                return l1;
            }
            list.next = l1;
        }
        if (l2 != null) {
            if (head == null) {
                return l2;
            }
            list.next = l2;
        }
        return head;
    }

    public static void main(String[] args) {
        // 输入：1->2->4, 1->3->4
        // 输出：1->1->2->3->4->4
        ListNode list1 = null;
        ListNode n1_1 = new ListNode(1);
        ListNode n1_2 = new ListNode(2);
        ListNode n1_3 = new ListNode(4);

        ListNode list2 = null;
        ListNode n2_1 = new ListNode(1);
        ListNode n2_2 = new ListNode(3);
        ListNode n2_3 = new ListNode(4);
        
        list1 = n1_1;
        n1_1.next = n1_2;
        n1_2.next = n1_3;
        
        list2 = n2_1;
        n2_1.next = n2_2;
        n2_2.next = n2_3;

        ListNode listNode = new Solution021_MergeTwoLists().mergeTwoLists(list1, list2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }
}
