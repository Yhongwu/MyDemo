package com.howard.binaryTree;

public class Node {
	public int data;
	public Node left;
	public Node right;
	//以下两个用于计算结点间最大距离
	//左子树距根结点的最大距离
	public int leftMaxDistance;
	//右子树距根结点的最大距离
	public int rightMaxDistance;
	public Node (int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}
