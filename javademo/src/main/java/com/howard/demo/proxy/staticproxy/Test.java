package com.howard.demo.proxy.staticproxy;

import com.howard.demo.proxy.UserService;
import com.howard.demo.proxy.UserServiceImpl;
/**
 * 静态代理测试类
 * @author yaohongwu
 *
 */
public class Test {
	public static void main(String[] args) {
		UserService target = new UserServiceImpl();
		UserServiceProxy proxy = new UserServiceProxy(target);
		proxy.save();
	}
}
