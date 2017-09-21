package com.howard.algorithm.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * 用户喜好
 * 2017年9月10日
 * @author hongwu
 */
public class UserLike {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long user_n = sc.nextLong();
//		List<Integer> user_k = new ArrayList<>();
		Map<String, Long> user_k = new HashMap<>();
		for (Long i = 0L ; i < user_n ; i ++ ) {
			Long k = sc.nextLong();
			user_k.put(i.toString(), k);
		}
		/*Set<Entry<String, Integer>> entrySet = user_k.entrySet();
		for (Entry<String, Integer> s : entrySet) {
			System.out.println(s.getKey()+"---"+s.getValue());
		}*/
		
		long q = sc.nextLong();
//		List<Integer[]> qq = new ArrayList<>();
		Map<String, Long[]> m = new HashMap<>();
		Set<Long> set = new HashSet<>();
		for (Long i = 0L ; i < q; i ++) {
			Long[] a = new Long[3];
			a[0] = sc.nextLong();
			a[1] = sc.nextLong();
			a[2] = sc.nextLong();
			set.add(a[2]);
//			qq.add(a);
			m.put(i.toString(),a);
		}
		sc.close();
		Map<String, Map<String,Long>> map = new HashMap<>();
		for (Long i = 0L ; i < user_k.size(); i ++ ) {
			Map<String, Long> m1 = new HashMap<>();
			Iterator<Long> iterator = set.iterator();
			if (i != 0L) {
				Long tmp = i - 1;
				Set<Entry<String, Long>> entrySet = map.get(tmp).entrySet();
				for (Entry<String, Long> e : entrySet) {
					m1.put(e.getKey(), e.getValue());
				}
			}
			while (iterator.hasNext()) {
				Long t = iterator.next();
				if (user_k.get(i.toString()).equals(t)) {
					if (m1.get(t.toString()) != null) {
						m1.put(t.toString(), m1.get(t.toString()) + 1);
					}else {
						m1.put(t.toString(), 1L);
					}
				}
			}
			map.put(i.toString(), m1);
		}
		for (Long i = 0L ; i < m.size() ; i ++) {
			Long[] a = m.get(i.toString());
			a[0] = a[0] - 1;
			a[1] = a[1] - 1;
			System.out.println(map.get(a[0].toString()).get(a[2].toString()));
			Long start = map.get(a[0].toString()).get(a[2].toString());
			Long end = map.get(a[1].toString()).get(a[2].toString());
			System.out.println(end - start);
		}
		
		
//		for (Long i = 0L ; i < m.size() ; i ++ ) {
//			Long[] a = m.get(i.toString());
//			int t = 0;
//			for (Long j = a[0] - 1; j < a[1]; j ++ ) {
//				if (user_k.get(j.toString()).equals(a[2])) {
//					t ++ ;
//				}
//			}
//			System.out.println(t);
//			t = 0;
//			
//		}
	}
}
/**
5
1 2 3 3 5
3
1 2 1
2 4 5
3 5 3

*/