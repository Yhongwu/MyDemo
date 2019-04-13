package com.howard.test.test04;

import java.util.Arrays;

/**
 * Created by Howard Yao on 2018/10/12.
 */
public class T1 {
    public static void main(String[] args) {
        String[] s1 = new String[]{"a","a","a"};
        String[] s2 = new String[s1.length];

        System.arraycopy(s1,0,s2,0,s1.length);
        s2[1] = "b";
        System.out.println(Arrays.toString(s1));
        System.out.println(Arrays.toString(s2));


    }

    public static String fun(String s) {
        s += "bbb";
        return s;
    }

}

class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}