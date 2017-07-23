package com.howard.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.howard.dao.UserDao;
import com.howard.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})  
public class TestDao {
	
	@Autowired
	private UserDao userdao;
	
	@Test
	public void testAddUser() {
		User u = new User();
		u.setUsername("howard");
		u.setPassword("123456");
		u.setAge(22);
		int insert = userdao.insert(u);
		//返回操作成功 >0表示插入成功
		System.out.println(insert);
		//返回插入的主键
		System.out.println(u.getId());
	}
	@Test
	public void testFingUsers() {
		User u = new User();
//		u.setUsername("tom");
		u.setAge(20);
		List<User> users = userdao.findUsers(u);
		for (User user : users) {
			System.out.println(user);
		}
	}
}
