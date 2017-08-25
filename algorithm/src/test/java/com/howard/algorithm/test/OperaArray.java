package com.howard.algorithm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OperaArray {
	public static void main(String[] args) {
//		int o = 4;
//		int[] a ={1,2,3,4};
		Scanner in = new Scanner(System.in);
		long size = in.nextInt();
//		int a[] = new int[size];
		List<Long> list = new ArrayList<>();
		for (long i = 0 ; i < size; i ++ ) {
			long a = in.nextInt();
			list.add(a);
			list = rev(list);
		}
//		for (int i = 0 ; i < a.length; i ++ ) {
//			
//			list.add(a[i]);
//			list = rev(list);
//		}
		for(int i = 0 ;i < list.size() - 1 ; i ++ ) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println(list.get(list.size() - 1));
		in.close();
	}
	public static List<Long> rev(List<Long> list) {
		List<Long> l = new ArrayList<>();
		for (int i = list.size() - 1; i >= 0 ; i -- ) {
			l.add(list.get(i));
		}
		return l;
	}
}
