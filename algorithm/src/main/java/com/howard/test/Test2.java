package com.howard.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hongwu on 2018/3/14.
 */
public class Test2 {

    public static void main(String[] args) {
       /* Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }*/
        Integer a[] = {4,1,3,5,4};
        int max = 0;
        int min = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < a.length; i ++ ) {
            for (int j =  i+1 ; j < a.length; j ++ ) {
                if (list.size() == 0 ) {
                    max = min = a[j];
                }else if (max < a[i]) {
                    max = a[j];
                }else if (min > a[i]) {
                    min = a[j];
                }
                list.add(a[j]);

            }
        }
    }

}
