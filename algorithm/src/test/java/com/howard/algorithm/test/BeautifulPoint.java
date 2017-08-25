package com.howard.algorithm.test;

import java.util.Scanner;
/**
 * 优雅的点
 * 时间限制：1秒
 * 空间限制：32768K
 * 小易有一个圆心在坐标原点的圆，小易知道圆的半径的平方。小易认为在圆上的点而且横纵坐标都是整数的点是优雅的，小易现在想寻找一个算法计算出优雅的点的个数，请你来帮帮他。
 * 例如：半径的平方如果为25
 * 优雅的点就有：(+/-3, +/-4), (+/-4, +/-3), (0, +/-5) (+/-5, 0)，一共12个点。
 * 输入描述:输入为一个整数，即为圆半径的平方,范围在32位int范围内。
 * 输出描述:输出为一个整数，即为优雅的点的个数
 * 输入例子1:25
 * 输出例子1:12
 * 2017年8月12日
 * @author hongwu
 */
public class BeautifulPoint {
	public static void main(String[] args) {
//		System.out.println(findBeautifulPoint(16));
		Scanner str = new Scanner(System.in);
		int num = str.nextInt();
		int count = 0;
		//数（+-x,+-y）等于4个点
		for (int i = 0 ; i*i <= num; i ++ ) {
			//只取比i大的 找到一对数（x,y）则为4*2
			for (int j = i ; j * i <= num; j ++ ) {
				if ((i * i) + (j * j) > num) {
					break;
				}
				if ((i * i) + (j * j) == num) {
					//+-0 一样
					if (i == 0 || j == 0) {
						count += 4;
					}else if( i == j){  
						count += 4;
					}else {
						count += 8;
					}
					break;
				}
				
			}
		}
		System.out.println(count);
		str.close();
	}
}
