package com.howard.demo.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
/**
 * 利用PropertyDescriptor操作javabean
 * 其内部就是反射原理
 * apache也提供了一个工具类beanutils来操作属性
 * @author Howard
 * 2017年2月18日
 */
public class ReflectJavaBeanTest {

	public static void main(String[] args) throws Exception {
		PointTest p = new PointTest(3,6);
		
		String propertyName = "x";
		//实现原理 x->X->getX->methodGetX
		//这里利用javabean内省工具 PropertyDescriptor属性描述
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, p.getClass());
		//获取读get方法
		Method readMethodX = pd.getReadMethod();
		Object retval = readMethodX.invoke(p);
		System.out.println(retval);
		
		//获取set方法
		Method writeMethodX = pd.getWriteMethod();
		writeMethodX.invoke(p, 5);
		
		System.out.println(p.getX());
		
		
	}
}
