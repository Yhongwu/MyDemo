package com.howard.common;
/**
 * 求1-n的素数
 * 1既不是素数，也不是合数
 * 素数：除了1和它本身以外不再有其他因数
 * @author hongwu
 */
public class PrimeNumber {
	public static void getPrime1(int end) {
		//1既不是素数，也不是合数
		boolean flag = true;
		int count = 0;
		for (int i = 2 ; i <= end; i ++) {
			for (int j = 2 ; j < i; j ++ ) {
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
	}
	public static void getPrime2(int end) {
		
	}
	public static void main(String[] args) {
		getPrime1(1000);
	}
}
