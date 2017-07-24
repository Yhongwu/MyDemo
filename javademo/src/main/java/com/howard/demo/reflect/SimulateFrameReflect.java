package com.howard.demo.reflect;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
/**
 * 利用反射模拟小框架
 * @author Howard
 * 2017年2月18日
 */
public class SimulateFrameReflect {
	public static void main(String[] args) throws Exception {
//		InputStream in = new FileInputStream("classpath:src/com/Howard/test02/config.properties");
		
		InputStream in = SimulateFrameReflect.class.getClassLoader().getResourceAsStream("com/howard/demo/reflect/config.properties");
		
		Properties props = new Properties();
		props.load(in);
		in.close();
		
		String className = props.getProperty("className");
		Collection collections = (Collection) Class.forName(className).newInstance();
		
		ArrayList a =  (ArrayList)Class.forName(className).newInstance();
		
		PointTest p1 = new PointTest(2, 2);
		PointTest p2 = new PointTest(3, 3);
		PointTest p3 = new PointTest(4, 4);
		
		collections.add(p1);
		collections.add(p2);
		collections.add(p3);
		
		a.add(p1);
		a.add(p2);
		a.add(p3);
		
		System.out.println(collections.size());
		System.out.println(a.size());
	}
	
}
