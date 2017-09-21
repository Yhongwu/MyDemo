package com.howard.algorithm.test;

/**
 * 反转链表
 * 2017年9月18日
 * @author hongwu
 */
public class ReverseList {
    public static ListNode reverseList(ListNode head) {
    	ListNode node = head;
    	ListNode tmp = head.next;
    	while (node.next != null) {
    		node = tmp;
    		tmp.next = node;
    		tmp = node.next;
    	}
		return node;
    	
    }
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		ListNode reverseList = reverseList(node1);
		System.out.println(";;;");
		while(reverseList.next != null) {
			System.out.println(reverseList.val);
		}
	}
}
