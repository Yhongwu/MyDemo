package com.howard.demo.proxy.jdkproxy;

import com.howard.test.proxy.UserService;
import com.howard.test.proxy.UserServiceImpl;

public class Test {
	public static void main(String[] args) {
		//打开这个配置  可以把生成$Proxy0的class文件保存下来
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); 
		
		//只能代理接口对象
		UserService service = (UserService) new JDKProxy().getInstance(new UserServiceImpl());
		System.out.println(service.getClass().getName()); //com.sun.proxy.$Proxy0
		service.save();
	}
}
