package com.howard.springmvc.converter;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class DateBindingInitializer implements WebBindingInitializer{

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		//注册自定义的属性编辑器
		binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd"));
	}

}
