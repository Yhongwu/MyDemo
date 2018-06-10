package com.howard.demo.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * cglib动态代理
 * @author yaohongwu
 *
 */
public class CglibProxy implements MethodInterceptor {
	
	private Object target;
	
	public Object getInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(this);
		Object proxy = enhancer.create();
		return proxy;
	}
	
	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		Object obj = null;
		System.out.println("操作前的预处理...");
        obj = method.invoke(target, args);
        System.out.println("操作后的处理...");
        return obj;
	}

}
