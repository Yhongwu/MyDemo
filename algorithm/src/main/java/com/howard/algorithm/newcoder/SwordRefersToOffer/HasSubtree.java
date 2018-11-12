package com.howard.algorithm.newcoder.SwordRefersToOffer;
/**
 * 剑指offer
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * @author yaohongwu
 *
 */
public class HasSubtree {
	public static void main(String[] args) {
		/**
		 *     1
		 *   2   3
		 *  4 5  6
		 *    
		 *    
		 *    2
		 *   4 5
		 */
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t2.right = t5;
		t3.left = t6;
		
		
		TreeNode tt2 = new TreeNode(2);
		TreeNode tt4 = new TreeNode(4);
		TreeNode tt5 = new TreeNode(5);
		tt2.left = tt4;
		tt2.right = tt5;
		System.out.println(hasSubtree(t1, tt2));
		
	}
	public static boolean hasSubtree(TreeNode root1,TreeNode root2) {
		boolean result = false;
		if (root1 != null && root2 != null) {
			if (root1.val == root2.val) {
				result = checkTree(root1, root2);
			}
			if (!result) {
				result = hasSubtree(root1.left, root2);
			}
			if (!result) {
				result = hasSubtree(root1.right, root2);
			}
		}
		
		return result;
		
	}
	public static boolean checkTree(TreeNode root1,TreeNode root2) {
		//先检测root2
		//待检测的树如果已经结束，返回true
		if (null == root2) {
			return true;
		}
		if (null == root1) {
			return false;
		}
		if (root1.val != root2.val) {
			return false;
		}
		return checkTree(root1.left, root2.left) && checkTree(root1.right, root2.right);
		
	}
}
