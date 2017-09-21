package com.howard.demo.constructobject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 不通过new方式创建对象 方法3：
 * 通过反序列化方式创建对象
 * 不调用构造方法
 * 2017年8月29日
 * @author hongwu
 */
public class Test3 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	public Test3() {
		this.name = "howard";
		System.out.println("construct");
	}
	@Override
	public String toString() {
		return "Test3 [name=" + name + "]";
	}
	public static void main(String[] args) {
		Test3 t = new Test3();
		System.out.println(t);
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			FileOutputStream fos = new FileOutputStream("test3");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(t);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Test3 tt ;
		try {
			FileInputStream fis = new FileInputStream("test3");
			ois = new ObjectInputStream(fis);
			tt = (Test3)ois.readObject();
			System.out.println(tt);
			if (tt != t) {
				System.out.println("two different object");
			}
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
