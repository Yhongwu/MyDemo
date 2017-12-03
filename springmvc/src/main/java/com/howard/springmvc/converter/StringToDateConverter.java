package com.howard.springmvc.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 * 类型转换器
 * 使用spring的converter
 * 2017年12月3日
 * @author hongwu
 */
public class StringToDateConverter implements Converter<String, Date>{
	//日期模板 如yyyy-MM-dd
	private String datePattern;
	
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	//重写Converter<S, T>接口的类型转换方法
	@Override
	public Date convert(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(this.datePattern);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("日期转换失败！");
			return null;
		}
	}

}
