package com.howard.algorithm.test;

import java.util.ArrayList;

public class PrintMatrix {
	private static ArrayList<Integer> list = new ArrayList<>();
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
    	int x = matrix.length;
    	int y = matrix[0].length;
    	int start = 0;
    	while (x > start*2 && y > start*2) {
    		printCircle(matrix, x, y, start);
    		start++;
    	}
		return list;
        
    }
    public static void printCircle(int [][] matrix,int x,int y,int start) {
    	System.out.println("1");
    	for (int i = start; i < y - start - 1; i ++) {
    		list.add(matrix[start][i]);
    	}
    	for (int i = start; i < x - start - 1; i ++) {
    		list.add(matrix[i][y-start-1]);
    	}
    	for (int i = y - start - 1; i > start; i --) {
    		list.add(matrix[x-start-1][i]);
    	}
    	for (int i = x - start - 1; i > start; i --) {
    		list.add(matrix[i][start]);
    	}
    }
    public static void main(String[] args) {
//		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    	int[][] matrix = {{1}};
		printMatrix(matrix);
		for (Integer i : list) {
			System.out.print(i+" ");
		}
	}
}
