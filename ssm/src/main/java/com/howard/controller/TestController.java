package com.howard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.howard.common.util.ResponseUtils;
import com.howard.entity.User;
import com.howard.service.UserService;

@Controller
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("test")
	public String testJump() {
		return "success";
	}
	
	@RequestMapping("user")
	public String list() {
		return "crud/user_list";
	}
	@RequestMapping("user_list") 
	public @ResponseBody String userList() {
		User u = new User();
		List<User> findUsers = userService.findUsers(u);
		String jsonString = JSON.toJSONString(findUsers);
		return jsonString;
	}
	
	@RequestMapping("user_list2") 
	public void userList2(HttpServletRequest request, HttpServletResponse response) {
		User u = new User();
		List<User> findUsers = userService.findUsers(u);
		String jsonString = JSON.toJSONString(findUsers);
		ResponseUtils.renderJson(response, jsonString);
//		return jsonString;
	}
	
	@RequestMapping("user_add_view") 
	public String userAdd() {
		return "crud/user_add";
	}
	
	@RequestMapping("user_add_save") 
	public String userSave(User user) {
		userService.create(user);
		return "crud/user_list";
	}
}
