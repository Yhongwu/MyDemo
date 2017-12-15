package com.howard.demo.proxy;
/**
 * 对象
 * 代理目标对象
 * @author yaohongwu
 *
 */
public class UserServiceImpl implements UserService{

	@Override
	public void save() {
		System.out.println("保存操作...");
	}

}
