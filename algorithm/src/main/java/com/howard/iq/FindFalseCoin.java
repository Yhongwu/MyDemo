package com.howard.iq;
/**
 * 找出假硬币
 * 假设有n枚硬币 其中只有一枚假的 假设假的比较轻 
 * 现要求找出假硬币 只有一个天平可以称 
 * 这道题可以采用分治法：
 * 如果总数为偶数，
 * 将这堆硬币分为两堆，分别放于天平左右称，轻的那边就有假的 然后用轻的那边继续递归
 * 如果总数为偶数，
 * 分为三堆，中间一个单独拿开 左右两个称，如果左右两堆相等，则中间那个是假的 否则按继续拿轻的递归 
 * @author Howard
 * 2017年3月31日
 */
public class FindFalseCoin {
	public static void main(String[] args) {
		int coin[] = {2,2,2,2,2,1};
		System.out.print("硬币为");
		for (int i : coin) {
			System.out.print(i+"  ");
		}
		System.out.println();
		int falseCoin = FalseCoin(coin, 0, coin.length-1);
		if (falseCoin != -1) {
			System.out.println("第"+(falseCoin+1)+"枚为假硬币");
		}else {
			System.out.println("没找到");
		}
	}
	public static int FalseCoin(int coin[],int low,int high) {
		int i,sum1,sum2,coin3;
		int falseCoin = -1;
		sum1 = sum2 = coin3 = 0;
		//只有两个 直接比较即可
		if (low + 1 == high) {
			if (coin[low] < coin[high]) {
				falseCoin = low;
				//return falseCoin;
			}else {
				falseCoin = high;
				//return falseCoin;
			}
		}
		//偶数
		if ((high - low + 1) % 2 == 0) {
			for (i = low; i <= low + (high - low)/2; i++) {
				sum1+=coin[i];
			}
			for (i = low + (high - low)/2 +1; i <= high; i++) {
				sum2+=coin[i];
			}
			if (sum1 > sum2) {
				falseCoin = FalseCoin(coin, low + (high - low)/2 +1, high);
				//return falseCoin;
			}else {
				//sum1 > sum2
				falseCoin = FalseCoin(coin, low, low + (high - low)/2);
				//return falseCoin;
			}
		}else {
			//奇数个 分为三堆
			//计算前半堆的和
			for (i = low; i <= low + (high - low)/2 -1; i++) {
				sum1 += coin[i];
			}
			//计算后半堆的和
			for (i = low + (high - low)/2 + 1; i <= high; i++) {
				sum2 += coin[i];
			}
			//中间的索引
			coin3 = low + (high - low)/2;
			if (sum1 > sum2) {
				falseCoin = FalseCoin(coin, low + (high - low)/2, high);
				//return falseCoin;
			}else if (sum1 < sum2) {
				falseCoin = FalseCoin(coin, low, low + (high - low)/2 - 1);
				//return falseCoin;
			}else if (sum1 + coin[coin3] == sum2 + coin[coin3]) {
				falseCoin = coin3;
				//return falseCoin;
			}
			
		}
		return falseCoin;
		
	}
}
