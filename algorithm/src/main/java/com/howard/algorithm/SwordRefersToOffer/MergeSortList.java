package com.howard.algorithm.SwordRefersToOffer;

/**
 * 剑指offer
 * 合并两个排序的链表 
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * 2017年9月24日
 * @author hongwu
 */
public class MergeSortList {
	/**
	 * 非递归版
	 * @param list1
	 * @param list2
	 * @return
	 */
    public static ListNode Merge(ListNode list1,ListNode list2) {
    	ListNode head = null;
    	ListNode list = null;
    	if (list1 != null && list2 != null) {
    		if (list1.val < list2.val) {
    			list = list1;
    			list1 = list1.next;
        	}else {
        		list = list2;
    			list2 = list2.next;
        	}
    		head = list;
    	}else if(list1 == null && list2 != null) {
    		return list2;
    	}else if (list1 != null && list2 == null) {
    		return list1;
    	}
    	while(list1 != null && list2 != null) {
    		if (list1.val < list2.val) {
    			list.next = list1;
    			list1 = list1.next;
    		}else {
    			list.next = list2;
    			list2 = list2.next;
    		}
    		if (head == null) {
				head = list;
			}
    		list = list.next;
    	}
    	if (list1 != null) {
    		list.next = list1;
    	}
    	if (list2 != null) {
    		list.next = list2;
    	}
		return head;
    }
    /**
     * 递归版
     * 参考网友
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode MergeII(ListNode list1,ListNode list2) {
		if(list1 == null){
		    return list2;
		}
		if(list2 == null){
			return list1;
		}
		if(list1.val <= list2.val){
			list1.next = Merge(list1.next, list2);
			return list1;
		}else{
			list2.next = Merge(list1, list2.next);
			return list2;
		}        
	}
    /**
     * 非递归版本 思路差不多
     * 参考网友
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode MergeIII(ListNode list1,ListNode list2) {
		if(list1 == null){
		    return list2;
		}
		if(list2 == null){
			return list1;
		}
		ListNode mergeHead = null;
        ListNode current = null;    

        while(list1!=null && list2!=null){
        	 if(list1.val <= list2.val){
        	      if(mergeHead == null){
        	            mergeHead = current = list1;
        	       }else{
        	            current.next = list1;
        	            current = current.next;
        	       }
        	       list1 = list1.next;
        	  }else{
        	       if(mergeHead == null){
        	            mergeHead = current = list2;
        	       }else{
        	            current.next = list2;
        	            current = current.next;
        	       }
        	        list2 = list2.next;
        	  }
       }
	   if(list1 == null){
	       current.next = list2;
	   }else{
	       current.next = list1;
	   }
	   return mergeHead;     
	}
    
    public static void main(String[] args) {
    	ListNode node1 = new ListNode(1);
		ListNode node3 = new ListNode(3);
		ListNode node5 = new ListNode(5);
//		ListNode node7 = new ListNode(7);
//		ListNode node9 = new ListNode(9);
		node1.next = node3;
		node3.next = node5;
//		node5.next = node7;
//		node7.next = node9;
//		
		ListNode node2 = new ListNode(2);
		ListNode node4 = new ListNode(4);
		ListNode node6 = new ListNode(6);
//		ListNode node8 = new ListNode(8);
//		ListNode node10 = new ListNode(10);
		node2.next = node4;
		node4.next = node6;
//		node6.next = node8;
//		node8.next = node10;
//		
//		ListNode merge = Merge(node1, node2);
		ListNode merge = MergeII(node1, node2);
		while(merge != null) {
			System.out.println(merge.val);
			merge = merge.next;
		}
	}
    
    
    
}
