package com.howard.algorithm.test;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
	 private static String[] strArray = {"ZERO","ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE"};

	    public static void main(String[] args) {
	        String str = "ZEROTWOONE";
	        List<Integer> list = new ArrayList<>();
	        int start = 0;
	        System.out.println(str.substring(7,7+3));
	        for (int i = 0 ; i < str.length() - 2 ;) {
	            for (int j = 0 ; j < strArray.length ; j ++ ) {
	                if (i == 7) {
	                    System.out.println("hehe");
	                    System.out.println(str.substring(i, i + 3));
	                }
	                if (((i + 3) < str.length() ) && str.substring(i, i + 3).equals(strArray[j])){
	                    System.out.println(str.substring(i, i + 3));
	                    //list.add(j < 8 ? i + 10 - 8 : i - 8);
	                    i += 3;
	                    System.out.println("00"+i);
	                    break;
	                }else if (((i + 4) < str.length()) && str.substring(i, i + 4).equals(strArray[j])) {
	                    System.out.println(str.substring(i, i + 4));
	                 //   list.add(j < 8 ? i + 10 - 8 : i - 8);
	                    i += 4;
	                    System.out.println(i);
	                    break;
	                }else if (((i + 5) < str.length()) && str.substring(i,i + 5).equals(strArray[j])){
	                    System.out.println(strArray[j]);
	                 //   list.add(j < 8 ? i + 10 - 8 : i - 8);
	                    i += 5;
	                    break;
	                }
	            }
	        }
	        for (int i : list) {
	            System.out.println(i);
	        }

	    }
}
