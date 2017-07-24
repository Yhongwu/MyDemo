package com.howard.demo.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;



public class ReflectTest {
	
	public static void main(String[] args) throws Exception{
//		getNameAndPackage();
//		getClazz();
//		getConstructor();
//		getField();
		practiceChangeString();
//		getMethod();
//		TestMain();
//		testArray();
		
//		practiceArray("abc");
//		practiceArray(new String[]{"1","2","3"});
		
//		getSuperClass();
	}
	
	/**
	 * 获取类名以及包名
	 */
	public static void getNameAndPackage(){
		System.out.println(PointTest.class.getName());
		//com.Howard.test02.PointTest
	}
	
	/**
	 * 三种获取class字节码的方式
	 * 相关比较
	 * @throws ClassNotFoundException
	 */
	public static void getClazz() throws ClassNotFoundException{
		Class<?> clazz_1 = PointTest.class;
		Class<?> clazz_2 = new PointTest().getClass();
		//抛ClassNotFoundException异常
		Class<?> clazz_3 = Class.forName("com.Howard.test02.PointTest");
		
		System.out.println(clazz_1.getName());
		System.out.println(clazz_2.getName());
		System.out.println(clazz_3.getName());
		
		//比较
		System.out.println(clazz_1 == clazz_2);
		System.out.println(clazz_2 == clazz_3);
		
		String s = "abc";
		//isPrimitive()是否为基本类型
		System.out.println("string是否为基本类型:"+s.getClass().isPrimitive());
		
		System.out.println(int.class == Integer.class);
		System.out.println(int.class == Integer.TYPE);
		System.out.println("int是否为基本类型:"+int.class.isPrimitive());
		System.out.println("int[]是否为基本类型:"+int[].class.isPrimitive());
		System.out.println("int[]是否为数组:"+int[].class.isArray());
		
		
	}
	/**
	 * 获取构造方法并通过构造方法new出实例对象
	 * @throws Exception
	 */
	public static void getConstructor() throws Exception{
		//String str = new String("abc");
		//传入参数类型
		Constructor<String> constructor_1 = String.class.getConstructor(StringBuffer.class);
		//通过构造器来实例化对象
		//如果上面得到的Constructor没有明确泛型，下面就必须强制转换
		//参数必须明确new出类型 直接用字符串是会报argument type mismatch异常
		String str = constructor_1.newInstance(new StringBuffer("abc"));
		System.out.println(str.charAt(2));
	}
	/**
	 * 获取成员变量Field
	 * 注意private的获取方式
	 * @throws Exception
	 */
	public static void getField() throws Exception{
		PointTest poit = new PointTest(5, 7);
		
		Field fieldY = poit.getClass().getField("y");
		//这时得到的fieldY只是一个变量 并不能说它的值为5
		System.out.println(fieldY);
		System.out.println(fieldY.getType());
		//得到值必须指定哪个具体对象
		System.out.println(fieldY.get(poit));
		System.out.println(fieldY.getInt(poit));
		//使用下面的方法获取private的变量x，会认为看不到这个变量 在获取具体信息时会报java.lang.NoSuchFieldException: x
//		Field fieldX = poit.getClass().getField("x");
		
		Field fieldX = poit.getClass().getDeclaredField("x");
		//获取权限修饰符
		int a = fieldX.getModifiers();
		System.out.println(Modifier.toString(a));
		
		//使用getDeclaredField获取x后，在获取值时仍会报xxx with modifiers "private"
		//private 无访问权限 所以通过下面的方法先强制访问
		fieldX.setAccessible(true);

		System.out.println(fieldX.getType());
		System.out.println(fieldX.get(poit));
		
	}
	/**
	 * 练习 
	 * 将PointTest中string类型的值中b改为z
	 * @throws Exception
	 */
	public static void practiceChangeString() throws Exception{
		PointTest poit = new PointTest();
		
		System.out.println("old:"+poit);
		
		//获取所有成员变量
		Field[] fields = poit.getClass().getDeclaredFields();
		for(Field field:fields){
			//字节码比较 用==
			if(field.getType() == String.class){
				String oldvalue =(String)field.get(poit);
				String newvalue = oldvalue.replace("b", "z");
				field.set(poit, newvalue);
			}
		}
		
		System.out.println("new:"+poit);
	}
	/**
	 * 获取方法method并执行
	 * @throws Exception
	 */
	public static void getMethod() throws Exception{
		//反射实现如下语句
//		String str = "abc";
//		str.charAt(2);
		
		String str = "abc";
		//第一个参数 方法名 第2~n个参数 参数类型
		Method methodCharAt = String.class.getMethod("charAt", int.class);
		System.out.println(methodCharAt.invoke(str, 2));
		System.out.println(methodCharAt.invoke(str, new Object[]{2}));
	}
	
	/**
	 * 获取com.Howard.test02.ArgumentsTest的main方法并传参数执行
	 * @throws Exception
	 */
	public static void TestMain() throws Exception{
		ArgumentsTest.main(new String[]{});
		
		System.out.println(ArgumentsTest.class.getName());
		
		Method method = ArgumentsTest.class.getMethod("main", String[].class);
		//下面的参数这样写会报错java.lang.NoSuchMethodException 
		//因为jdk会自动拆箱为多个参数，但是该方法只要一个数组参数
		//method.invoke(null, new String[]{"123","234"});
		
		//下面这样写会把参数包成一个数组
		method.invoke(null, new Object[]{new String[]{"123","234"}});
		method.invoke(null, (Object)new String[]{"123","234"});

	}
	/**
	 * 数组与Object的关系以及数组的反射
	 */
	public static void testArray(){
		int[] a1 = new int[]{1,2,3,};
		int[] a2 = new int[4];
		int[][] a3 = new int[2][3];
		String[] a4 = new String[]{"a","b","c","d"};
		System.out.println(a1.getClass() == a2.getClass());
		//下面三者无法比较 
//		System.out.println(a2.getClass() == a3.getClass());
//		System.out.println(a3.getClass() == a4.getClass());
//		System.out.println(a4.getClass() == a1.getClass());
		
		//[I  [表示数组 I表示int
		System.out.println(a1.getClass().getName());
		System.out.println(a3.getClass().getName());
		System.out.println(a1.getClass().getSuperclass().getName());
		System.out.println(a2.getClass().getSuperclass().getName());
		
		//引伸
		Object aObj1 = a1;
		Object aObj2 = a4;
		Object aObj3 = a3;
		//下面这个编译就过不去
//		Object[] aObj4 = a1;
		Object[] aObj5 = a3;
		Object[] aObj6 = a4;
		
		//下面的结果是：
		//[I@15db9742
		//[Ljava.lang.String;@6d06d69c
		System.out.println(a1);
		System.out.println(a4);
		
		//如果想看到数组里面的具体值。使用arrays工具类：
		//结果：
		//[[I@15db9742]
		//[a, b, c, d]
		//asList接收的是T...a
		//根据上面Object测试结果int数组是被当作Object的，而string数组可以被当作Object数组的，所以会有下面的结果
		System.out.println(Arrays.asList(a1));
		System.out.println(Arrays.asList(a4));
	
	}
	/**
	 * 数组反射 获取类并判断是否为数组 如果是 输出各个数
	 * @param obj
	 */
	public static void practiceArray(Object obj){
		Class clazz = obj.getClass();
		System.out.println(clazz.getName());
		if(clazz.isArray()){
			System.out.println(clazz.isArray());
			int len = Array.getLength(obj);
			for(int i = 0;i<len;i++){
				System.out.println(Array.get(obj, i));
			}
		}else{
			System.out.println(obj);
		}
	}
	/**
	 * 获取父类以及接口
	 * @throws Exception
	 */
	public static void getSuperClass() throws Exception{
		Class<?> clazz = Class.forName("com.Howard.test02.PointTest");
		
		//获取父类
		Class<?> superclass = clazz.getSuperclass();
		
		Class<?>[] interfaces = clazz.getInterfaces();
		
		System.out.println("父类："+superclass.getName());
		System.out.println("实现的接口：");
		for(int i = 0;i<interfaces.length;i++){
			System.out.println(interfaces[i]+" ");
		}
		
		
		/*
		父类：java.lang.Object
                         实现的接口：
        interface java.io.Serializable 
		 */
		
		
		
	}
	

}
