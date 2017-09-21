package com.howard.algorithm.SwordRefersToOffer;

/**
 * 剑指offer
 * 
 * 输入一个链表，输出该链表中倒数第k个结点。
 * 思路：(前提判别各种极限情况)用2个指针，一个先走k步，第二个指针开始指向起始点，同时遍历，
 * 当第一个指针走到尽头的时候，第二个指针所指位置即为倒数第k个结点，
 * 当然java中没指针这个概念，这里借用来说明思路
 * 2017年9月17日
 * @author hongwu
 */
public class FindKthToTail {
    public static ListNode FindKthToTail(ListNode head,int k) {
    	if (head == null || k <= 0) {
    		return null;
    	}
    	ListNode node1 = head;
    	ListNode node2 = head;
    	int i = 1;
    	while (i < k && node1 != null) {
    		node1 = node1.next;
    		i ++ ;
    	}
    	if (node1 == null) {
    		return null;
    	}
    	while (node1.next != null) {
    		node2 = node2.next;
    		node1 = node1.next;
    	}
		return node2;
    }
    
    public static void main(String[] args) {
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(3);
		ListNode node3 = new ListNode(4);
		ListNode node4 = new ListNode(5);
		ListNode node5 = new ListNode(6);
		ListNode node6 = new ListNode(7);
		ListNode head = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		ListNode findKthToTail = FindKthToTail(head, 1);
		System.out.println(findKthToTail==null?"null":findKthToTail.val);
	}
}
//class ListNode {
//    int val;
//    ListNode next = null;
//
//    ListNode(int val) {
//        this.val = val;
//    }
//}