package com.howard.algorithm;

import java.util.*;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/a6a656249f404eb498d16b2f8eaa2c60
 * 设有n个正整数，将他们连接成一排，组成一个最大的多位整数。
 * 如:n=3时，3个整数13,312,343,连成的最大整数为34331213。
 * 如:n=4时,4个整数7,13,4,246连接成的最大整数为7424613。
 * 输入描述：
 * 有多组测试样例，每组测试样例包含两行，第一行为一个整数N（N<=100），第二行包含N个数(每个数不超过1000，空格分开)。
 * 输出描述：
 * 每组数据输出一个表示最大的整数。
 * 示例：
 * 输入：
 * 2
 * 12 123
 * 4
 * 7 13 4 246
 * 输出：
 * 12312
 * 7424613
 * Created by hongwu on 2017/9/4.
 */
public class NumOfString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        List<String> list = new ArrayList<>();
        for (int i = 0 ; i < num; i ++ ) {
            list.add(scanner.next());
        }
        scanner.close();
        //这里注意一个问题，比如93 936 排在前面的应该是93而不是936，所以重写两两之间的比较方法
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        //int[] counts = {12, 321, 234};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = num - 1 ; i >= 0 ; i -- ) {
            stringBuilder.append(list.get(i));
        }
        System.out.print(stringBuilder.toString());
    }

}
//15
//160 973 672 929 11 466 295 249 440 635 965 45 47 61 674
//97396592967467263561474664544029524916011

//37
//93 672 946 153 947 82 522 772 725 413 936 649 399 123 821 93 29 860 572 344 608 590 554 210 115 307 313 535 636 184 291 66 178 379 204 523 758
//9479469393936860828217727587256726664963660859057255453552352241339937934431330729291210204184178153123115