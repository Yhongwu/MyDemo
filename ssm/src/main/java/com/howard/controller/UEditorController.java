package com.howard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("ueditor")
public class UEditorController {
	@RequestMapping("ueditor")
	public String ueditor() {
		return "/ueditor";
	}
}
