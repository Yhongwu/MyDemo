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

	/**
	 * 筛选法求素数
	 * @param end
	 */
	public static void getPrimeII(int end) {
		//先假定所有数是素数 下标表示数
		//true表示为素数
		boolean[] a = new boolean[end+1];
		for (int i = 0 ; i < a.length; i ++ ) {
			a[i] = true;
		}
		// 0 1不是素数 2是最小的素数
		//素数的倍数都不是素数
		//2是素数 那么2的倍数都不是素数  3是素数 3的倍数也不是素数
		a[0] = a[1] = false;
		for (int i = 2 ; i * i <= end; i ++) {   //Math.sqrt(end)
			if (a[i]) {
				for (int j = i + i ; j <= end; j += i ) {
					a[j] = false;
				}
			}
		}

		//格式化输出
		int k = 0;
		for (int i = 2 ; i < a.length; i ++ ) {
			if (a[i] ) {
				if (k != 0 && k % 10 ==0) {
					System.out.println();
				}
				k ++;
				System.out.printf("%4d",i);

			}
		}
		System.out.println();
		System.out.println("count:"+k);
	}

	public static void main(String[] args) {
		getPrime(1000);
		System.out.println("----------------------------------");
		getPrimeII(1000);
	}
}
