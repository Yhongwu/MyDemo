package com.howard.algorithm.newcoder.SwordRefersToOffer;


import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下矩阵：
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class PrintMatrix {
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        int x = 0;
        int y = 0;
        int endx = matrix.length - 1;
        int endy = matrix[0].length - 1;

        //只需关注左上角和右下角的边界 每打印一圈 边界分表向对角线方向靠拢 直到x <= endx && y <= endy
        while (x <= endx && y <= endy) {
            print(matrix,x++,y++,endx--,endy--,list);
        }
        return list;
    }

    public static void print(int [][] matrix,int x,int y,int endx,int endy,ArrayList<Integer> list) {
        if (x == endx) { //只有1行的情况
            for (int i = y; i <= endy; i ++) {
                list.add(matrix[x][i]);
            }
        }else if (y == endy) { //只有1列的情况
            for (int i = x; i <= endx; i ++) {
                list.add(matrix[i][y]);
            }
        }else {
            for (int i = x; i <= endy; i ++ ) {
                //System.out.println(matrix[x][i]);
                list.add(matrix[x][i]);
            }
            for (int i = x + 1 ; i <= endx; i ++) {
                //System.out.println(matrix[i][endy]);
                list.add(matrix[i][endy]);
            }
            for (int i = endy - 1; i >= y; i --) {
                //System.out.println(matrix[endx][i]);
                list.add(matrix[endx][i]);
            }
            for (int i = endx - 1; i > x ; i --) {
                //System.out.println(matrix[i][y]);
                list.add(matrix[i][y]);
            }
        }


    }

    public static void main(String[] args) {
        int a[][] = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20}};

        //int a[][] = {{1,2,3}};
        ArrayList<Integer> arrayList = printMatrix(a);
        for (Integer i : arrayList) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}