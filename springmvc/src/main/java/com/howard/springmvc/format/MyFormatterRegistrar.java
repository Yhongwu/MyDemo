package com.howard.springmvc.format;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
/**
 * 方式2：使用formatterRegistrar注册Formatter
 * 2017年12月3日
 * @author hongwu
 */
public class MyFormatterRegistrar implements FormatterRegistrar{
	private DateFormatter dateFormatter;
	
	public void setDateFormatter(DateFormatter dateFormatter) {
		this.dateFormatter = dateFormatter;
	}

	@Override
	public void registerFormatters(FormatterRegistry registry) {
		registry.addFormatter(dateFormatter);
	}

}
