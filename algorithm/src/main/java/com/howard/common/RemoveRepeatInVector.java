package com.howard.common;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
/**
 * 去除Vector中重复的元素
 * 三种方式
 * @author Howard
 * 2017年3月8日
 */
public class RemoveRepeatInVector {
	public static void main(String[] args) {
		Vector<String> v = new Vector<>();
		v.add("aa");
		v.add("bb");
		v.add("cc");
		v.add("aa");
//		v = RemoveRepeatInVectorMathod2(v);
//		v = RemoveRepeatInVectorMathod3(v);
		v = RemoveRepeatInVectorMathod4(v);
		for(int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));
		}
//		int i=5;
//		int j = 10;
//		System.out.println(~j);
	}	
	/**
	 * 这种泛型的具体类型写死了
	 * @param vector
	 * @return
	 */
	public static Vector<String> RemoveRepeatInVectorMathod1(Vector<String> vector) {
		Vector<String> temp = new Vector<>();
		for(int i = 0; i < vector.size(); i++) {
			String str = vector.get(i);
			if (!temp.contains(str)) {
				temp.add(str);
			}
		}
		return temp;
	}
	/**
	 * 使用contains方式来判断
	 * @param vector
	 * @return
	 */
	public static <T> Vector<T> RemoveRepeatInVectorMathod2(Vector<T> vector) {
		Vector<T> temp = new Vector<>();
		for(int i = 0; i < vector.size(); i++) {
			T t = vector.get(i);
			if (!temp.contains(t)) {
				temp.add(t);
			}
		}
		return temp;
	}
	/**
	 * 使用HashSet方式辅助去除重复 再利用Vector的addAll方法
	 * @param vector
	 * @return
	 */
	public static <T> Vector<T> RemoveRepeatInVectorMathod3(Vector<T> vector) {
		Vector<T> temp = new Vector<>();
		HashSet<T> set = new HashSet<>(vector);
		temp.addAll(set);
		return temp;
	}
	/**
	 * 使用HashSet去除重复，再一次迭代返回vector
	 * @param vector
	 * @return
	 */
	public static <T> Vector<T> RemoveRepeatInVectorMathod4(Vector<T> vector) {
		Vector<T> temp = new Vector<>();
		HashSet<T> set = new HashSet<>(vector);
		temp = setToVector(set);
		return temp;
	}
	private static <T> Vector<T> setToVector(Set<T> set) {
		Vector<T> temp = new Vector<>();
		Iterator<T> iterator = set.iterator();
		while(iterator.hasNext()) {
			temp.add(iterator.next());
		}
		return temp;
	}
}
