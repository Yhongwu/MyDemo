package com.howard.demo.constructobject;
/**
 * 不通过new方式创建对象 方法1：
 * 调用对象的clone方法
 * 实现clone的类首先需要继承Cloneable接口，该接口是一个标识接口，无任何接口方法。
 * 类中重写clone方法
 * 在clone方法中调用
 * 结果可以看出 创建了新的对象 但没有调用构造方法
 * 2017年8月29日
 * @author hongwu
 */
public class Test1 {
	public static void main(String[] args) {
		Obj obj_1 = new Obj();
		Obj obj_2 = (Obj)obj_1.clone();
		obj_1.changeInt();
		System.out.println("obj_1:"+obj_1.getAint());
		System.out.println("obj_2:"+obj_2.getAint());
	}
}
class Obj implements Cloneable {
	private int aInt = 0;
	public Obj() {
		System.out.println("construct");
	}
	public int getAint() {
		return aInt;
	}
	public void setAint(int aInt) {
		this.aInt = aInt;
	}
	public void changeInt() {
		this.aInt = 1;
	}
	@Override
	protected Object clone(){
		Object o = null;
		try {
			o = (Obj)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
}