package com.howard.algorithm.SwordRefersToOffer;


/**
 * 剑指offer
 * 两个链表的第一个公共结点 
 * 输入两个链表，找出它们的第一个公共结点。
 * 思路：所以最后一个节点是一样的，先分别求出两个链表的长度 因为两个链表有公共部分
 * 然后先遍历长的链表到|size1-size2|处，然后两个链表同时遍历，过程中一边进行比较，如果有相同的即第一个相交节点，返回即可。
 * 如果没有找到，返回null
 * 2017年9月28日
 * @author hongwu
 */
public class FindFirstCommonNode {
    public static ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
    	 int size1 = 0;
    	 int size2 = 0;
    	 ListNode head1 = pHead1;
    	 ListNode head2 = pHead2;
    	 while (head1 != null) {
    		 size1 ++ ;
    		 head1 = head1.next;
    	 }
    	 while (head2 != null) {
    		 size2 ++ ;
    		 head2 = head2.next;
    	 }
    	 head1 = size1 - size2 > 0 ? pHead1 : pHead2;
    	 head2 = size2 - size1 < 0 ? pHead2 : pHead1;
    	 size1 = size1 - size2 > 0 ? size1 - size2 : size2 - size1;
    	 while (size1 > 0) {
    		 head1 = head1.next;
    		 size1 -- ;
    	 }
    	 while (head1 != null && head2 != null) {
    		 if (head1.val == head2.val && head1.next == head2.next) return head1;
    		 else {
    			 head1 = head1.next;
    			 head2 = head2.next;
    		 }
    	 }
    	 return null;
    }
    
    public static void main(String[] args) {
    	ListNode t1 = new ListNode(1);
    	ListNode t2 = new ListNode(3);
    	ListNode t3 = new ListNode(5);
    	ListNode t4 = new ListNode(6);
    	t1.next = t2;
    	t2.next = t3;
    	t3.next = t4;
    	ListNode tt1 = new ListNode(2);
    	ListNode tt2 = new ListNode(4);
    	tt1.next = tt2;
    	tt2.next = t3;
    	ListNode findFirstCommonNode = findFirstCommonNode(t1, tt1);
		System.out.println(findFirstCommonNode == null ? "null":findFirstCommonNode.val);
    }
}
