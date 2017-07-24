package com.howard.iq;

/**
 * 舍罕王赏麦
 * 有位宰相发明国际象棋给国王，国王想赏赐他。
 * 他提的赏赐是：在8*8的棋盘上，第一个格子1个麦，第二个格子2个，第三个格子4个，第四个格子8个，
 * 以此类推，后一个是前一个的两倍
 * @author Howard
 * 2017年3月31日
 */
public class Mai {
	public static void main(String[] args) {
		int n = 64;
		double count = MaiAlgorithm(n);
		//这里应该舍入取整
		System.out.println("棋盘格子数："+n+" 麦子总数"+count);
	}
	public static double MaiAlgorithm(int n) {
		double temp = 1;
		double sum = 0;
		
		for (int i = 0; i < n; i ++) {
			temp *= 2;
			sum += temp;
		}
		return sum;
	}
}
