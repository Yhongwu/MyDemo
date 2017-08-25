package com.howard.algorithm.test;

import java.util.Scanner;

public class MaxSubStringWith0Or1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }
       // String s = in.next();
        String s = "1000001101";
        int max = 0;
        int tmp = 1;
        for (int i = 1 ; i < s.length(); i ++ ) {
        	if (s.substring(i, i+1).equals(s.substring(i-1,i))) {
        		if (max < tmp) {
        			max = tmp;
        		}
        		tmp = 1;
        	}else {
        		tmp ++ ;
        		if (i == (s.length() - 1)) {
        			if (max < tmp) {
            			max = tmp;
            		}
        		}
        	}
        }
        System.out.println(max);
    }
}
