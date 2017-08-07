package com.howard.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlFilterUtils {
	/**
	 * 去除所有html标签
	 * @param input
	 * @param length
	 * @return
	 */
	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "......";
		}
		return str;
	}
	
	/**
	 * 替换两个以上的空格符为换行符
	 * @param str
	 * @return
	 */
	public static String replaceBlankString(String str) {      
        if(str!=null && !"".equals(str)) {      
            Pattern p = Pattern.compile("\\s{2,}|\t|\r|\n");      
            Matcher m = p.matcher(str);      
            String strNoBlank = m.replaceAll("<br><br>"); 
            return strNoBlank;      
        }else {      
        	
            return str;      
        }           
    }
	/**
	 * 去除文本中的超链接
	 * @param str
	 * @return
	 */
	public static String removeATag(String str) {
		str = str.replaceAll("<a[^>]*>", "").replaceAll("</a[^>]*>", "");
		return str;
	}
	

}
