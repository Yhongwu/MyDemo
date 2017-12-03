package com.howard.springmvc.converter;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 类型转换器 
 * 自定义属性编辑器
 * 使用java.beans的PropertyEditor
 * 2017年12月3日
 * @author hongwu
 */
public class DateEditor extends PropertyEditorSupport{
	//日期模板 如yyyy-MM-dd
	private String datePattern;
	
	public DateEditor(String datePattern) {
		System.out.println("--");
		this.datePattern = datePattern;
	}
	
	//将传入的字符串数据转换成Date类型
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(this.datePattern);
		try {
			System.out.println(text);
			Date date = dateFormat.parse(text);
			setValue(date);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("日期转换失败！");
		}
	}
}
