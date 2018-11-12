package com.howard.algorithm.newcoder.leetcode;

/**
 *
 *  merge-sorted-array
 *
 *  Given two sorted integer arrays A and B, merge B into A as one sorted array.
 *  Note:
 *  You may assume that A has enough space to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
 */
public class MergeSortedArray {

    public static void merge(int A[], int m, int B[], int n) {
        int x = m + n - 1;
        m = m - 1;
        n = n - 1;
        while (m >= 0 && n >= 0)
        {
            if (A[m] > B[n]) {
                A[x--]  = A[m--];
            }else {
                A[x--] =
                        B[n--];
            }
        }
        while(m >= 0) {
            A[x--] = A[m--];
        }
        while(n >= 0) {
            A[x--] = B[n--];
        }
    }

    public static void main(String[] args) {
        //假设数据空间足够大
        int A[] = new int[8];
        A[0] = 1;
        A[1] = 3;
        A[2] = 5;
        A[3] = 7;

        int B[] = {2,4,6,8};
        //m和n分别为数组A和B中元素的个数
        merge(A,4,B,4);
        for(Integer i : A) {
            System.out.println(i);
        }
    }
}
