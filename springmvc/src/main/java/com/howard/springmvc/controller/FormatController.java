package com.howard.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.howard.springmvc.vo.FormatUser;
import com.howard.springmvc.vo.User;

@Controller
@RequestMapping("format")
public class FormatController {
	private static final Log logger = LogFactory.getLog(FormatController.class);
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(@ModelAttribute User user,Model model) {
		System.out.println("format");
		logger.info(user);
		model.addAttribute("user",user);
		return "success";
	}
	
	@RequestMapping(value="formatter",method=RequestMethod.POST)
	public String formatter(@ModelAttribute FormatUser user,Model model) {
		System.out.println("format");
		logger.info(user);
		model.addAttribute("user",user);
		return "format/success";
	}
}
