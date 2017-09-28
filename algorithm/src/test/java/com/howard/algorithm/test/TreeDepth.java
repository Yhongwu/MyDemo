package com.howard.algorithm.test;

public class TreeDepth {
	
    public int treeDepth(TreeNode root) {
        if (root.left == null && root.right == null) {
        	return 1;
        }
        if ((root.left != null && root.right == null) || 
        		(root.left == null && root.right != null)) {
        	return 2;
        }
        else return Math.max(treeDepth(root.left) + 1, treeDepth(root.right) + 1);
        
    }
}
