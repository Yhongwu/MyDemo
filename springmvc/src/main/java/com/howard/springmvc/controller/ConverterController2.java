package com.howard.springmvc.controller;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.howard.springmvc.converter.DateEditor;
import com.howard.springmvc.vo.User;

@Controller
@RequestMapping("converter2")
public class ConverterController2 {
	private static final Log logger = LogFactory.getLog(ConverterController.class);
	
	//在controller控制器初始化时注册属性编辑器
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		System.out.println("initBinder");
		//注册自定义属性编辑器
		binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd"));
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(@ModelAttribute User user,Model model) {
		logger.info(user);
		model.addAttribute("user",user);
		return "success";
	}
}
