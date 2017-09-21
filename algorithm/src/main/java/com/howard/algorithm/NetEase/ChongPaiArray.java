package com.howard.algorithm.NetEase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 重排数组
 * 2017年9月13日
 * 
 * 网易2018校招
 * 小易有一个长度为N的正整数数列A = {A[1], A[2], A[3]..., A[N]}。
 * 牛博士给小易出了一个难题:
 * 对数列A进行重新排列,使数列A满足所有的A[i] * A[i + 1](1 ≤ i ≤ N - 1)都是4的倍数。
 * 小易现在需要判断一个数列是否可以重排之后满足牛博士的要求。
 * 
 * 例子：
 * 输入：
 * 3
 * 1 10 100
 * 4
 * 1 2 3 4
 * 输出：
 * Yes
 * No
 * 
 * 思路来自网友
 * 有两种情况：
 * 1、任何数和4的倍数相乘，结果仍然是4的倍数
 * 2、两个2的倍数的数相乘，结果是4的倍数
 * 所以解答思路分下面2中情况：
 * 1、不存在2的倍数，那么1 4 1 4 1和1 4 1 4都是满足情况的，即numOfFour >= (nuumOfOdd - 1)
 * 2、存在2的倍数 ，那么所有2的倍数放在一起，后面的是奇数和4的倍数，这样只要满足 numOfFour >= nuumOfOdd即可，如：
 *   2 2 2 4 1 4 1 或 2 4 1 4 即最后一个2后面一定跟着4的倍数，故 numOfFour >= nuumOfOdd
 * @author hongwu
 */
public class ChongPaiArray {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<String> list = new ArrayList<>(n);
		for (int i = 0 ; i < n ; i ++ ) {
			long count = sc.nextLong();
			long numOfTwo = 0;
			long numOfFour  = 0;
			for (long j = 0 ; j < count; j ++ ) {
				long a = sc.nextLong();
				if (a % 4 == 0) {
					numOfFour ++;
				}else if (a % 2 == 0) {
					numOfTwo ++ ;
				}
			}
			long numOfOdd = count - numOfFour - numOfTwo;
			if ((numOfTwo == 0 && numOfFour >= (numOfOdd - 1) ) || numOfFour >= numOfOdd) {
				list.add("Yes");
			}else {
				list.add("No");
			}
		}
		sc.close();
		for (String str : list) {
			System.out.println(str);
		}
	}
}
/**
2
3
1 10 100
4
1 2 3 4
*/