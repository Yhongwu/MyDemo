package com.howard.common;
/**
 * 单例模式
 * 饱汉模式
 * @author Howard
 * 2017年3月9日
 */
public class SingleTon {
	private SingleTon(){}
	private final static SingleTon instance = new SingleTon();
	public static SingleTon getInstance() {
		return instance;
	}
}
/**
 * 饿汉模式
 * @author Howard
 * 2017年3月9日
 */
class SingleTon2{
	private SingleTon2(){}
	private static SingleTon2 instance = null;
	public static synchronized SingleTon2 SingTonGeInstance() {
		if (instance == null) 
			instance = new SingleTon2();
		return instance;
	}
}
