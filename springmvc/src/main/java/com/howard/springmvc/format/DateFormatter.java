package com.howard.springmvc.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date>{
	
	//日期模板对象 yyyy-MM-dd
	private String datePattern;
	//日期格式化对象
	private SimpleDateFormat dateFormat;
	
	public DateFormatter(String datePattern) {
		super();
		this.datePattern = datePattern;
		this.dateFormat = new SimpleDateFormat(datePattern);
	}

	@Override
	public String print(Date date, Locale locale) {
		return dateFormat.format(date);
	}

	@Override
	public Date parse(String source, Locale locale) throws ParseException {
		
		return dateFormat.parse(source);
	}

}
