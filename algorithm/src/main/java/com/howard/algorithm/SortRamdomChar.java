package com.howard.algorithm;

import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;
/**
 * 
 * 随机生成20个不重复的小写字母并进行排序，排序方式为倒序。
 * 欢聚yy 2017校招笔试c卷
 * 2017年9月2日
 * @author hongwu
 */
public class SortRamdomChar {
	private static char getRamdomChar(Random rdm) {
		//产生97到122的随机数a-z值; 
		int i = Math.abs(rdm.nextInt()) % 26 + 97; //ascall码 大写 A 65 小写 a 97
//		i = i % 26;
//		i = i + 97;
		return (char)i;
	}
	public static void main(String[] args) {
		Random rdm = new Random();
//		System.out.println(getRamdomChar(rdm));
		TreeSet<Character> treeSet = new TreeSet<>(new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				if (o1 > o2) {
					return -1;
				}else if (o1 < o2) {
					return 1;
				}else {
					return 0;
				}
			}
			
		});
		//set 元素不可重复
		while(treeSet.size() < 20) {
			treeSet.add(getRamdomChar(rdm));
		}
		for (Object c : treeSet.toArray()) {
			System.out.print(c + " ");
		}
	}
}
