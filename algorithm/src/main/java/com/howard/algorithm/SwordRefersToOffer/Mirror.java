package com.howard.algorithm.SwordRefersToOffer;


/**
 * 剑指offer
 * 二叉树镜像
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 源二叉树： 
 *     	    8
 *  	   /  \
 *   	  6   10
 *   	 / \  / \
 *   	5  7 9 11
 * 镜像二叉树：
 *   	    8
 *   	   /  \
 *   	  10   6
 *   	 / \  / \
 *   	11 9 7  5
 * 2017年9月23日
 * @author hongwu
 */
public class Mirror {
    public void mirror(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	TreeNode tmp = root.left;
    	root.left = root.right;
    	root.right = tmp;
    	mirror(root.left);
    	mirror(root.right);
    }
    public static void main(String[] args) {
		//...
	}
}
