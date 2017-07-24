package com.howard.demo.other;
/**
 * java基本数据类型的字节、取值范围
 * 由于java.lang.Boolean没用提供size方法，
 * 所以这里只具体列出了8种基本数据类型中的7种
 * @author Howard
 * 2017年2月24日
 */
public class BasicDataTypeTest {

	public static void main(String[] args) {
		System.out.println(Integer.TYPE+":"+Integer.SIZE
				+"  "+Integer.MIN_VALUE+"~"+Integer.MAX_VALUE);
		System.out.println(Short.TYPE+":"+Short.SIZE
				+"  "+Short.MIN_VALUE+"~"+Short.MAX_VALUE);
		System.out.println(Byte.TYPE+":"+Byte.SIZE
				+"  "+Byte.MIN_VALUE+"~"+Byte.MAX_VALUE);
		System.out.println(Long.TYPE+":"+Long.SIZE
				+"  "+Long.MIN_VALUE+"~"+Long.MAX_VALUE);
		System.out.println(Character.TYPE+":"+Character.SIZE
				+"  "+(int)Character.MIN_VALUE+"~"+(int)Character.MAX_VALUE);
		System.out.println(Float.TYPE+":"+Float.SIZE
				+"  "+Float.MIN_VALUE+"~"+Float.MAX_VALUE);
		System.out.println(Double.TYPE+":"+Double.SIZE
				+"  "+Double.MIN_VALUE+"~"+Double.MAX_VALUE);
	}
}
