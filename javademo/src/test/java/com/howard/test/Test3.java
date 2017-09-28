package com.howard.test;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
	public static void main(String[] args) {
		List<Integer> newList = new ArrayList<>();
		for (int i = 5 ; i < 20; i ++) {
			newList.add(i);
		}
		List<Integer> list = new ArrayList<>(newList);
		System.out.println("ok");
//		list.get(0)
	}
}
