package com.howard.demo.reflect;

import java.io.Serializable;

/**
 * 用于java反射的测试类
 * @author Howard
 * 2017年2月17日
 */
public class PointTest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	public int y;
	
	public String str_1 = "alibaba";
	public String str_2 = "basketball";
	public String str_3 = "tomorrow";
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public PointTest(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public PointTest() {
		super();
	}
	@Override
	public String toString() {
		return "PointTest [x=" + x + ", y=" + y + ", str_1=" + str_1 + ", str_2=" + str_2 + ", str_3=" + str_3 + "]";
	}
	
	

}
