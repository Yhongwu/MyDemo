package com.howard.common;
/**
 * 求1-n的素数
 * 1既不是素数，也不是合数
 * 素数：除了1和它本身以外不再有其他因数
 * cvte面试题
 * @author hongwu
 */
public class PrimeNumber {
	public static void getPrime(int end) {
		boolean flag = true;
		int count = 0;
		if (end >= 2) {
			System.out.printf("%4d",2);
			count++;
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
			//格式化输出 每10个换行
			if(flag) {
				if (count % 10 == 0 && count != 0) {
					System.out.println();
				}
				System.out.printf("%4d",i);
				count ++ ;
			}
			flag = true;
		}
		System.out.println("\n 总数："+count);
	}
	public static void main(String[] args) {
		getPrime(1000);
	}
}
