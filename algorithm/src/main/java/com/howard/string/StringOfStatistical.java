package com.howard.string;
/**
 * 字符串统计
 * 分别统计英文字符中文字符数字字符数量
 * 假设除了这些不会出现其它特殊字符
 * @author Howard
 * 2017年3月9日
 */
public class StringOfStatistical {
	public static void main(String[] args) {
		String str = "我们是abc124厉害的231中国chinese人";
		fun(str);
	}
	
	public static void fun(String str){
		int englishCount = 0;
		int chineseCount = 0;
		int digitCount = 0;
		for(int i = 0; i <str.length();i++) {
			char ch = str.charAt(i);
			if (ch >= '0' && ch <= '9') {
				digitCount++;
			}else if ((ch >= 'a' && ch <= 'z') || (ch >='A'&& ch <='Z')) {
				englishCount++;
			}else {
				chineseCount++;
			}
		}
		
		System.out.println("数字字符数量："+digitCount);
		System.out.println("英文字符数量："+englishCount);
		System.out.println("中文字符数量："+chineseCount);
	}
}
