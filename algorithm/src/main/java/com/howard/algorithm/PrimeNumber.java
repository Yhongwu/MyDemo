package com.howard.algorithm;

import java.util.ArrayList;

public class PrimeNumber {
    /**
     * 方法1：排除偶数
     * @param end
     */
    public static void getPrime(int end) {
        ArrayList<Integer> list = new ArrayList<>();
        boolean flag = true;
        if (end >= 2) {
            list.add(2);
        }
        //1既不是素数，也不是合数
        //偶数除了2之外都不是素数
        for (int i = 3 ; i <= end; i += 2) {
            for (int j = 3 ; j <= Math.sqrt(i); j ++ ) {
                if (i % j == 0 ) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                list.add(i);
            }
            flag = true;
        }

        printf(list);
    }

    /**
     * 方法2 筛选法
     * O(nlognlogn)
     * 1).若X是素数，则X的倍数都不是素数
     * 2).若X不是素数，则X肯定在[1,X）之间被筛选掉。即存在Y，使得k*Y=X，然后Y根据推理1) 判定X为非素数。
     * =》若X未被事先筛选掉则X为素数
     * 由当前的去筛选后面的
     * @param end
     */
    public static void getPrimeII(int end) {
        ArrayList<Integer> list = new ArrayList<>();
        boolean prime[] = new boolean[end+1];
        for (int i = 0 ; i < prime.length; i ++ ) {
            prime[i] = true;
        }
        for (int i = 2; i * i <= end; i ++ ) {
            if (prime[i]) {
                for (int j = 2 * i; j <= end; j += i) {
                    prime[j] = false;
                }
            }
        }
        for (int i = 2 ; i < prime.length; i ++ ) {
            if (prime[i]) {
                list.add(i);
            }
        }
        printf(list);
    }



    public static void printf(ArrayList<Integer> list) {
        for (int i = 0 ; i < list.size(); i ++) {
            if (i != 0 && i % 10 == 0) {
                System.out.println();
            }
            System.out.printf("%4d",list.get(i));
        }
        System.out.println();
        System.out.println("总数："+list.size());
    }

    public static void main(String[] args) {
        getPrime(100);
        getPrimeII(100);
        getPrimeIII(100);
    }
}
