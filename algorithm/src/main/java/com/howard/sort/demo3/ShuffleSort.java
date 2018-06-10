package com.howard.sort.demo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Howard Yao on 2018/3/20.
 */
public class ShuffleSort {

    /**
     * 数组随机排序
     * @param a
     */
    public static void shuffle(int[] a) {
        Random random = new Random();
        int index;
        int tmp;
        for (int i = 0 ; i < a.length; i ++) {
            index = random.nextInt(i + 1);
            tmp = a[i];
            a[i] = a[index];
            a[index] = tmp;
        }

    }

    /**
     * 使用Collections.shuffle(list)实现数组随机排序
     * @param a
     */
    public static void shuffleII(int[] a) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i : a) {
            list.add(i);
        }
        Collections.shuffle(list);
        int k = 0;
        for (int i : list) {
            a[k++] = i;
        }

    }

    public static void main(String[] args) {
        int a[] = {1,2,3,4,5,6,7,8};
        //shuffle(a);
        shuffleII(a);
        for (int i : a) {
            System.out.print(i+" ");
        }
    }
}
