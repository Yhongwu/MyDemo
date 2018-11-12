package com.howard.algorithm.newcoder.SwordRefersToOffer;

import java.util.ArrayList;
/**
 * 剑指offer
 * 输入一个链表，从尾到头打印链表每个节点的值。
 * 思路：1、遍历，存入arraylist，倒序输出
 * 2、使用递归
 * 2017年9月17日
 * @author hongwu
 */
public class PrintListFromTailToHead {
	private static ArrayList<Integer> arrayList = new ArrayList<Integer>();
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode != null) {
        	printListFromTailToHead(listNode.next);
        	arrayList.add(listNode.val);
        }
        return arrayList;
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
		printListFromTailToHead(node1);
		for (Integer i : arrayList) {
			System.out.println(i);
		}
	}
}
//class ListNode {
//	int val;
//	ListNode next = null;
//	ListNode(int val) {
//		this.val = val;
//	}
//}