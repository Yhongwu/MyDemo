package com.howard.algorithm.SwordRefersToOffer;


/**
 * 剑指offer
 * 复杂链表的复制 
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 * 思路：有两种解法
 * 第一种：哈希法：时间复杂度O(N),空间复杂度O(N),遍历一次，生成复制的结点，存到哈希中，比如1节点生成hash的key为1，value为节点1
 * 此时各复制节点的next和rand为指向空。原链表没变，这时候再遍历一次，将next和rand正确指向。最后返回hash表中头节点那个key即可。
 * 第二种：不需要额外的空间。第一次遍历：在原链表上复制，比如1-2-3-4-5遍历后为1-1'-2-2'-3-3'-4-4'-5-5'
 * 此时复制节点的next和rand都是错的。第二次遍历，因为通过1可以找到1’如此下去，所以第二次遍历可以正确的复制rand指针。
 * 第三次遍历是将链表拆分，同时然复制后的链表的next正确指向，让原链表回复原样。
 * 2017年9月28日
 * @author hongwu
 */
public class CloneRandomList {
    public static RandomListNode clone(RandomListNode pHead) {
    	if (pHead == null) {
    		return null;
    	}
    	RandomListNode head = pHead;
    	RandomListNode tmp = null;
    	RandomListNode cloneHead = null;
    	//在每一个原来的节点后复制一个数值一样的节点
    	//比如1 2 3 4 5 复制后 1 1 2 2 3 3 4 4 5 5
    	while (head != null) {
    		tmp = new RandomListNode(head.label);
    		tmp.next = head.next;
    		head.next = tmp;
    		head = tmp.next;
    	}
    	//设置新复制节点的随机指向
    	head = pHead;
    	while (head != null) {
    		tmp = head.next;
    		tmp.random = head.random != null? head.random.next : null; //这一步记得判空
    		head = tmp.next;
    	}
    	//分离链表
    	head = pHead;
    	tmp = head.next;
    	while (head != null) {
    		if (cloneHead == null) {
    			cloneHead = tmp;
    		}else {
    			tmp.next = head.next;
    			tmp = tmp.next;
    		}
    		head.next = head.next.next;
    		head = head.next;
    	}
    	tmp.next = null;
    	return cloneHead;
    }
    
    public static void main(String[] args) {
    	RandomListNode t1 = new RandomListNode(1);
    	RandomListNode t2 = new RandomListNode(2);
    	RandomListNode t3 = new RandomListNode(3);
    	RandomListNode t4 = new RandomListNode(4);
    	RandomListNode t5 = new RandomListNode(5);
    	t1.next = t2;
    	t1.random = t3;
    	t2.next = t3;
    	t2.random = t1;
    	t3.next = t4;
    	t3.random = t5;
    	t4.next = t5;
    	t4.random = t3;
    	t5.next = null;
    	t5.random = t2;
    	RandomListNode clone = clone(t1);
    	while(clone != null) {
    		System.out.print(clone.label+" ");
    		clone = clone.next;
    	}
	}
}
