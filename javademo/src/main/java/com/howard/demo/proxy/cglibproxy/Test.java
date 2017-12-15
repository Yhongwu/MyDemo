package com.howard.demo.proxy.cglibproxy;

import com.howard.test.proxy.UserServiceImpl;

public class Test {
	public static void main(String[] args) {
		//可以代理对象
		UserServiceImpl service = (UserServiceImpl) new CglibProxy().getInstance(new UserServiceImpl());
		//获取到的是代理对象 com.howard.test.proxy.UserServiceImpl$$EnhancerByCGLIB$$6aa6fa4f
		System.out.println(service.getClass().getName());
		service.save();
	}
}
