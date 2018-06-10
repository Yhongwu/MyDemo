package com.howard.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 未完成
 * Created by hongwu on 2017/9/5.
 */
public class PhoneSpare {
    private static String[] strArray = {"ZERO","ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String[] str = new String[num];
        int[][] strInt = new int[num][];
       for (int i = 0 ; i < num; i ++ ) {
            str[i] = sc.next();
            strInt[i] = new int[str[i].length()];
        }
       /*  for (int i = 0 ; i < num; i ++ ) {
            System.out.println(str[i]);
            for (int j = 0 ; j < strInt[i].length; j ++ ) {
                System.out.println(strInt[i][j]);
            }
        }*/
        for (int x = 0 ; x < num ; x ++ ) {
           // String str = "OHWETENRTEO"; //one two three 3 4 5
           // int[] strInt = new int[str.length()];
            List<Integer> list = new ArrayList<>();
            int flag;
            /*for (int i = 8 ; i <= 9 ; i ++ ) {
                flag = 1;
                int j = 0;
                for (; j < strArray[i].length(); j ++ ) {
                    int k = str[x].indexOf(strArray[i].substring(j,j+1));
                    if (k != -1 && strInt[x][k] != 1) {
                        strInt[x][k] = 1;
                    }else if (k == -1 || ((k != -1) && (strInt[x][k] == 1))) {
                        flag = 0;
                        break;
                    }
                }
                if (flag == 1) {
                    list.add(i - 8);
                }
            }*/
            //OHEWTIEGTHENRTEO eight one two three
            for (int i = 0; i < 8 ; i ++ ) {
                flag = 1;
                int j = 0;
                for (; j < strArray[i].length(); j ++ ) {
                    System.out.println(strArray[i].substring(j,j+1));
                    int k = str[x].indexOf(strArray[i].substring(j,j+1));
                    System.out.println(k);
                    if (k == -1 || ((k != -1) && (strInt[x][k] == 1))) {

                        System.out.println("///"+strInt[x][k]);
                        flag = 0;
                        break;
                    }
                }
                if (flag == 1) {
                    for (int y = 0 ; y < strArray[i].length(); y ++ ) {
                        int k = str[x].indexOf(strArray[i].substring(y,y+1));
                        System.out.println("oook"+k);
                        strInt[x][k] = 1;

                    }
                    list.add((i + 10) - 8);
                }
            }
            for (int i : list) {
                System.out.print(i);
            }
            System.out.println();
        }


    }
}
