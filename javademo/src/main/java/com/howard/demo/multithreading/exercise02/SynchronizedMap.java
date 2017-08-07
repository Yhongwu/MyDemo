package com.howard.demo.multithreading.exercise02;

import java.util.HashMap;
import java.util.Map;
/**
 * 在Java中Lock接口比synchronized块的优势是什么？
 * 你需要实现一个高效的缓存，它允许多个用户读，但只允许一个用户写，以此来保持它的完整性，你会怎样去实现它？
 * 2017年7月31日
 * @author hongwu
 * @param <K>
 * @param <V>
 */
public class SynchronizedMap<K,V> {
	private final Map<K, V> map = new HashMap<>();
	
	public synchronized void put(K key,V value) {
		map.put(key, value);
	}
	
	public synchronized V get(K key) {
		return map.get(key);
	}
}
