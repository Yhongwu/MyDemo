package com.howard.demo.proxy.staticproxy;

import com.howard.demo.proxy.UserService;

/**
 * 静态代理
 * @author yaohongwu
 *
 */
public class UserServiceProxy implements UserService{
	
	private UserService target;
	
	public UserServiceProxy(UserService target) {
		super();
		this.target = target;
	}

	@Override
	public void save() {
		System.out.println("操作前的预处理...");
		target.save();
		System.out.println("操作后的处理...");
		
	}

}
