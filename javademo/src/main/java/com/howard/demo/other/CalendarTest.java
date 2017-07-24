package com.howard.demo.other;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 关于日期的一些查询和简单操作格式化
 * @author Howard
 * 2017年2月12日
 */
public class CalendarTest {
	public static void main(String[] args) {
		Calendar time = Calendar.getInstance();
		System.out.println(time.getTime());
//		System.out.println(time.getWeekYear());
		
		//获取年 月 日 时 分 秒
		System.out.println(time.get(Calendar.YEAR));
		System.out.println(time.get(Calendar.MONTH));
		System.out.println(time.get(Calendar.DAY_OF_MONTH));
		System.out.println(time.get(Calendar.HOUR));
//		System.out.println(time.get(Calendar.HOUR_OF_DAY));
		System.out.println(time.get(Calendar.MINUTE));
		System.out.println(time.get(Calendar.SECOND));
		
		//1970年1月1日0时0分0秒到现在的毫秒数
		System.out.println(time.getTimeInMillis());
		System.out.println(System.currentTimeMillis());
		
		//获取当前月的最好一天 当前为第几周（以年看 以本月看）当前年最大天数
		System.out.println(time.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println(time.getActualMaximum(Calendar.DAY_OF_WEEK));
		System.out.println(time.getActualMaximum(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println(time.getActualMaximum(Calendar.DAY_OF_YEAR));
		
		//格式化日期
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String t = format.format(new Date());
		System.out.println(t);
		
		//打印昨天的当前时刻
		time.add(Calendar.DATE, -1);
		System.out.println(time.getTime());
	}
}
