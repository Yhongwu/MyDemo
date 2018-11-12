package com.howard.algorithm.newcoder.leetcode;

import com.howard.algorithm.newcoder.leetcode.type.TreeNode;

import java.util.ArrayList;

/**
 * binary-tree-preorder-traversal
 *  Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree{1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return[1,2,3].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
public class PreorderTraversal {
    public static ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        preorderTraversal(root,list);
        return list;
    }

    public static void preorderTraversal(TreeNode root,ArrayList<Integer> list) {
        if (root == null) {
            return ;
        }
        list.add(root.val);
        preorderTraversal(root.left,list);
        preorderTraversal(root.right,list);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        ArrayList<Integer> list = preorderTraversal(t1);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
