package com.howard.algorithm.SwordRefersToOffer;


/**
 * 剑指offer
 * 反转链表
 * 输入一个链表，反转链表后，输出链表的所有元素。
 * 思路:参考网上。当前节点是head，pre为当前节点的前一节点，next为当前节点的下一节点。
 * 先用next保存head的下一个节点的信息，保证单链表不会因为失去head节点的原next节点而就此断裂
 * 保存完next，就可以让head从指向next变成指向pre了，
 * head指向pre后，就继续依次反转下一个节点
 * 让pre，head，next依次向后移动一个节点，继续下一次的指针反转
 * 2017年9月18日
 * @author hongwu
 */
public class ReverseList {
    public static ListNode reverseList(ListNode head) {
    	if (head == null) {
    		return null;
    	}
    	ListNode pre = null;
    	ListNode next = null;
    	while (head != null) {
    		next = head.next;
    		head.next = pre;
    		pre = head;
    		head = next;
    	}
		return pre;
    	
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
			reverseList = reverseList.next;
		}
	}
}
