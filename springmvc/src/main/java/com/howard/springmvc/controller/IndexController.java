package com.howard.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping(value="index")
	public String ConverterIndex() {
		return "converter/registerform";
	}
	@RequestMapping(value="format")
	public String FormatIndex() {
		return "format/registerform";
	}
	@RequestMapping(value="format2")
	public String FormatIndex2() {
		return "format/formatter";
	}
}
