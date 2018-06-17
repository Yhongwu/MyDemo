package com.howard.algorithm.leetcode;

import com.howard.algorithm.leetcode.type.TreeNode;

/**
 * minimum-depth-of-binary-tree
 *
 * Given a binary tree, find its minimum depth.The minimum depth
 * is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class MinTreeDepth {

    public static int run(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        //这一步判断不能少
        //一边为空的情况下就不能取最少的 只能以不为空的计算 比如 [1,2,#,3],右子树为空，则结果为左子树的结点个数+1
        if (root.left == null)
            return run(root.right) + 1;
        else if (root.right == null)
            return  run(root.left) + 1;
        else
            return Math.min(run(root.left),run(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        //TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
       // t1.right = t3;
        t2.right = t4;
        t4.right = t5;
        int result = run(t1);
        System.out.println(result);
    }
}
