package com.howard.demo.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * jdk 动态代理
 * @author yaohongwu
 *
 */
public class JDKProxy implements InvocationHandler{
	
	//被代理类
	private Object target;
	
	//Object 可以接受不同的代理类
	public Object getInstance(Object target) {
		this.target = target;
		
		//Object java.lang.reflect.Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) 
		//注意这里创建代理对象时传入的第二个参数是代理对象的接口对象 jdk的proxy代理只能代理接口对象，所以被代理类都必须有实现接口
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object obj = null;
		System.out.println("操作前的预处理...");
		method.invoke(target, args);
		System.out.println("操作后的处理...");
		return obj;
	}

}
