package com.howard.algorithm.newcoder.SwordRefersToOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指offer
 * 从上往下打印二叉树 
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 * 思路：使用队列
 * Queue的方法 进队列offer 出队列poll
 * 2017年9月28日
 * @author hongwu
 */
public class PrintTreeFromTopToBottom {
    public static ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (root == null) {
        	return list;
        }
        list.add(root.val);
        queue.offer(root);
        while (!queue.isEmpty()) {
        	TreeNode poll = queue.poll();
        	if (poll.left != null) {
        		list.add(poll.left.val);
        		queue.offer(poll.left);
        	}
        	if (poll.right != null) {
        		list.add(poll.right.val);
        		queue.offer(poll.right);
        	}
        }
        return list;
    }
    
    public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		t1.left = t2;
		t1.right = t3;
		//t2.left = t4;
		t2.right = t5;
		t3.left = t6;
		
		ArrayList<Integer> printFromTopToBottom = printFromTopToBottom(t1);
		for (Integer i : printFromTopToBottom) {
			System.out.print(i+" ");
		}
	}
}
