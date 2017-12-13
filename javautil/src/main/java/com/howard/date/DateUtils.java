package com.howard.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * 获取线程安全的SimpleDateFormat
 * ？？                                                                                    
 * @author yaohongwu
 *
 */
public class DateUtils {
	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {      
		@Override       
		protected DateFormat initialValue() {      
			return new SimpleDateFormat("yyyy-MM-dd");       
		}   
	};
	/**
	 * 获取线程安全的SimpleDateFormat
	 * @return
	 */
	public static ThreadLocal<DateFormat> getDf() {
		return df;
	}   
	
}
