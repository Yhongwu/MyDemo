package com.howard.demo.multithreading.exercise02;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 在Java中Lock接口比synchronized块的优势是什么？
 * 1、lock接口在多线程和并发编程中最大的优势是它们为读和写分别提供了锁，
 * 它能满足你写像ConcurrentHashMap这样的高性能数据结构和有条件的阻塞。
 * 2、在于上下文的切换与锁的竞争的优化
 * synchronized在唤醒其他进程时不能指定，多个时只能用notifyAll()包括自己，让他们重新竞争，
 * 大部分重新竞争后又回到wait状态，会导致了大量的线程上下文切换。以及大量锁的竞争。
 * 而Lock可以通过Condition 获取特定锁，通过该条件锁wait和notufy可以唤醒指定的线程。
 * 3、synchronized效率比lock好些？？？
 * 你需要实现一个高效的缓存，它允许多个用户读，但只允许一个用户写，以此来保持它的完整性，你会怎样去实现它？
 * 2017年7月31日
 * @author hongwu
 * @param <K>
 * @param <V>
 */
public class LockMap<K,V> {
	private final Map<K, V> map = new HashMap<>();
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock r = lock.readLock();
	private final Lock w = lock.writeLock();
	
	public void put(K key,V value) {
		w.lock();
		try {
			map.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			w.unlock();
		}
	}
	
	public V get(K key) {
		r.lock();
		try {
			return map.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			r.unlock();
		}
		return null;
	}
	
}
