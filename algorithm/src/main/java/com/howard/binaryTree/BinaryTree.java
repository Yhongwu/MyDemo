package com.howard.binaryTree;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 二叉树
 * @author Howard
 * 2017年4月22日
 */
public class BinaryTree {
	private Node root;
	public BinaryTree() {
		root = null;
	}
	/**
	 * 将data插入到排序二叉树中
	 * @param data
	 */
	public void insert(int data) {
		Node newNode = new Node(data);
		if (root == null) {
			root = newNode;
		}else {
			Node current = root;
			Node parent;
			while(true) {
				parent = current;
				if (data < current.data) {
					current = current.left;
					if (current == null) {
						parent.left = newNode;
						return;
					}
				}else {
					current = current.right;
					if (current == null) {
						parent.right = newNode;
						return;
					}
				}
			}
		}
		
	}
	/**
	 * 购置二叉树
	 * @param data
	 */
	public void buildTree (int[] data) {
		for (int i = 0; i < data.length; i++) {
			insert(data[i]);
		}
	}
	/**
	 * 中序遍历
	 * @param localRoot
	 */
	public void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.left);
			System.out.print(localRoot.data + " ");
			inOrder(localRoot.right);
		}
	}
	public void inOrder() {
		this.inOrder(this.root);
	}
	/**
	 * 先序遍历
	 * @param localRoot
	 */
	public void preOrder(Node localRoot) {
		if (localRoot != null) {
			System.out.print(localRoot.data + " ");
			preOrder(localRoot.left);
			preOrder(localRoot.right);
		}
	}
	public void preOrder() {
		this.preOrder(this.root);
	}
	/**
	 * 后序遍历
	 * @param localRoot
	 */
	public void postOrder(Node localRoot) {
		if (localRoot != null) {
			postOrder(localRoot.left);
			postOrder(localRoot.right);
			System.out.print(localRoot.data + " ");
		}
	}
	
	public void postOrder() {
		this.postOrder(this.root);
	}
	/**
	 * 层序遍历
	 * 利用队列实现
	 * 先将根结点放入队列中，然后每次都从队列中取出一个结点打印该结点的值，若这个结点有子结点，则将他
	 * 的子结点放入队列尾，直到队列为空
	 */
	public void layerTranverse() {
		if (this.root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(this.root);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			System.out.print(node.data + " ");
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
	}
	
	public  int maxlen = 0;
	private int getMax(int a,int b) {
		return a > b ? a : b;
	}
	/**
	 * 求二叉树中结点的最大距离
	 * 结点的距离是值这两个结点之间边的个数 求一棵二叉树中相距最远的两个结点之间的距离
	 * 思路：首先求左子树距根结点的最大距离 ，其次求右子树距根结点的最大距离
	 * 则二叉树中结点的最大距离是两者之和
	 * 可以用递归实现
	 * @param root
	 */
	public void FindMaxDistance(Node root) {
		if (root == null) {
			return;
		}
		if (root.left == null) {
			root.leftMaxDistance = 0;
		}
		if (root.right == null) {
			root.rightMaxDistance = 0;
		}
		if (root.left != null) {
			FindMaxDistance(root.left);
		}
		if (root.right != null) {
			FindMaxDistance(root.right);
		}
		//计算左子树中距离根结点的最大距离
		if (root.left != null) {
			root.leftMaxDistance = getMax(root.left.leftMaxDistance, root.left.rightMaxDistance) + 1;
		}
		//计算右子树中距离根结点的最大距离
		if (root.right != null) {
			root.rightMaxDistance = getMax(root.right.leftMaxDistance, root.right.rightMaxDistance) + 1;
		}
		if (root.leftMaxDistance + root.rightMaxDistance > maxlen) {
			maxlen = root.leftMaxDistance + root.rightMaxDistance;
		}
	}
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree biTree = new BinaryTree();
		int[] data = {2,8,7,4,9,3,1,6,7,5};
		biTree.buildTree(data);
		System.out.println("中序遍历:");
		biTree.inOrder();
		System.out.println();
		System.out.println("先序遍历:");
		biTree.preOrder();
		System.out.println();
		System.out.println("后序遍历:");
		biTree.postOrder();
		System.out.println();
		System.out.println("层序遍历:");
		biTree.layerTranverse();
		System.out.println();
		biTree.FindMaxDistance(biTree.root);
		System.out.println(biTree.maxlen);
	}
}
