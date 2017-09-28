package com.howard.algorithm.test;
/**
 * 复杂链表
 * 一个指向下一节点
 * 一个指向随机节点
 * 2017年9月28日
 * @author hongwu
 */
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
