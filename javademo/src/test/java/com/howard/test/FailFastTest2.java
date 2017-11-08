package com.howard.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class FailFastTest2 {
	public static void main(String[] args) {
//		List<String> list = new ArrayList<>();
//		for (int i = 0 ; i < 10 ; i++ ) {
//			list.add(i + "");
//		}
//		Iterator<String> iterator = list.iterator();
//		int i = 0 ;
//		while(iterator.hasNext()) {
//			if (i == 3) {
//				iterator.remove();
//			}
//			System.out.println(iterator.next());
//			i ++;
//		}
//		
//		Map<String, String> map = new HashMap<>();
//		for (int i = 0 ; i < 10 ; i ++ ) {
//			map.put(i+"", i+"");
//		}
//		Iterator<Entry<String, String>> it = map.entrySet().iterator();
//		int i = 0;
//		while (it.hasNext()) {
//			if (i == 3) {
//				map.remove(3+"");
//			}
//			Entry<String, String> entry = it.next();
//			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//			i++;
//        }
		
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.remove(2);
		list.add(5);
		list.set(1, 100);
		list.remove(4);
		System.out.println(list.size());
		
	}
}
