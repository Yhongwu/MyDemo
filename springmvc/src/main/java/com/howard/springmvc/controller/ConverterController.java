package com.howard.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.howard.springmvc.vo.User;

@Controller
@RequestMapping("converter1")
public class ConverterController {
	private static final Log logger = LogFactory.getLog(ConverterController.class);
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(@ModelAttribute User user,Model model) {
		logger.info(user);
		model.addAttribute("user",user);
		return "success";
	}
}
