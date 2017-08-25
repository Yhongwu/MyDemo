package com.howard.algorithm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		List<Long> list = new ArrayList<>();
		for (int i = 0 ; i < size; i ++ ) {
			list.add(in.nextLong());
			int x = list.size() - 1;
			for (int j = 0 ; j < list.size()/2; j ++ ) {
//				long t = list.get(j);
//				list.set(j, list.get(x));
//				list.set(x, t);
				int xxx = list.size() - 1 - j;
				System.out.println(xxx+"--");
				System.out.println("--"+x);
				list.set(j, list.get(list.size() - 1 - j)+list.get(j));
				list.set(list.size() - 1 - j, list.get(j) - list.get(list.size() - 1 - j));
				list.set(j, list.get(j) - list.get(list.size() - 1 - j));
//				x -- ;
			}
		}
		for(int i = 0 ;i < list.size() - 1 ; i ++ ) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println(list.get(list.size() - 1));
		in.close();
	}
	public static List<Long> rev(List<Long> list) {
//		List<Long> l = new ArrayList<>();
//		for (int i = list.size() - 1; i >= 0 ; i -- ) {
//			l.add(list.get(i));
//		}
		int x = list.size() - 1;
		for (int i = 0 ; i < list.size()/2; i ++ ) {
			long a = list.get(i);
			list.set(i, list.get(x));
			list.set(x, a);
			x -- ;
		}
		return list;
	}
}
