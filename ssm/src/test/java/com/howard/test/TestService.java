package com.howard.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.howard.entity.User;
import com.howard.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)     
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})  
public class TestService {

	@Autowired
	private UserService userService;
	
	@Test
	public void testAddUser() {
		User u = new User();
		u.setUsername("tom");
		u.setPassword("654321");
		u.setAge(20);
		int insert = userService.create(u);
		System.out.println(insert);
	}
	
	@Test
	public void testFindUsers() {
		User u = new User();
		List<User> users = userService.findUsers(u);
		for (User user:users) {
			System.out.println(user);
		}
	}
}
