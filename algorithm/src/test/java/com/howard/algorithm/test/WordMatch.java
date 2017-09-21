package com.howard.algorithm.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.howard.test.stringtest;

public class WordMatch {
	public static void main(String[] args) {
		String str = "虎牙直播中国最大最好的互动直播平台。众多热门高清的游戏直播；上千款"
				+ "游戏，游戏大神齐聚虎牙。星光闪耀，顶尖赛事，综艺娱乐，美女秀场......不一样的精彩直播。";
		Scanner sc = new Scanner(System.in);
//		String str = sc.next();
		Set<String> wordMatch = wordMatch(str);
		StringBuffer sb = new StringBuffer();
		for (String s: wordMatch) {
			sb.append(s).append(",");
		}
		System.out.println(sb.toString().substring(0,sb.length()-1));
	}
	
	public static Set<String> wordMatch(String str) {
		List<String> list = new ArrayList<>();
		list.add("中国");
		list.add("直播");
		list.add("游戏");
		list.add("游戏直播");
		list.add("综艺娱乐");
		list.add("互动直播平台");
		Set<String> set = new HashSet<>();
		for (int i = 0 ; i < str.length() - 6; i += 6 ) {
			for (int j = 0 ; j < list.size(); j ++ ) {
//				System.out.println(str.substring(i, i+6));
				if (str.substring(i, i+6).indexOf(list.get(j))!=-1) {
					set.add(list.get(j));
					break;
				}
				
			}
		}
		return set;
	}
}
