package com.howard.iq;
/**
 * 鸡翁一，值钱5，鸡母一，值钱三，鸡雏三，值钱一，百钱买百鸡，问翁，母。雏各几何
 * @author Howard
 * 2017年3月31日
 */
public class BQBJ {
	public static void main(String[] args) {
		System.out.println("百钱买百鸡的可能结果：");
		BQBJAlgorithm(100, 100);
	}
	
	/**
	 * 求m钱买n只鸡 母鸡3文钱一只，公鸡5文钱一只 小鸡3只1文钱
	 * @param m
	 * @param n
	 */
	public static void BQBJAlgorithm(int m,int n) {
		//x 公鸡 y 母鸡 z小鸡
		int x,y,z; 
		for (x = 0; x <= n/5; x++) {
			for (y = 0; y <= n/3; y++) {
				z = n - x - y;
				if (z > 0 && z % 3 == 0 && x*5 + y* 3 + z/3 == m) {
					System.out.println("公鸡："+x+"只，母鸡："+y+"只，小鸡："+z+"只");
				}
			}
		}
	}
}
