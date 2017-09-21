package com.howard.algorithm.SwordRefersToOffer;

import java.util.Arrays;

/**
 * 剑指offer
 * 替换空格
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 2017年9月15日
 * @author hongwu
 */
public class ReplaceBlank {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("we are happy");
		System.out.println(replaceSpace2(sb));
	}
	/**
	 * 使用java提供的函数
	 * @param str
	 * @return
	 */
    public static String replaceSpace(StringBuffer str) {
    	String newStr = str.toString().replaceAll(" ", "%20");
		return newStr;
    }
    /**
     * 不使用java提供的函数
     * 思路：先统计str中空格数 然后从后遍历，遇空格替换字符
     * 注意这里转换为char数组后，新增%20必须先扩容
     * @param str
     * @return
     */
    public static String replaceSpace2(StringBuffer str) {
    	char[] chas = str.toString().toCharArray();
    	int num = 0;
    	for (int i = 0 ; i < chas.length; i ++ ) {
    		if (chas[i] == ' ') {
    			num ++ ;
    		}
    	}
    	char[] copyOf = Arrays.copyOf(chas, chas.length + 2 * num);
    	int len = chas.length + 2 * num - 1;
    	for (int i = chas.length - 1; i >= 0 ; i --) {
    		if (chas[i] != ' ') {
    			copyOf[len--] = copyOf[i];
    		}else {
    			copyOf[len--] = '0';
    			copyOf[len--] = '2';
    			copyOf[len--] = '%';
    		}
    	}
    	str = new StringBuffer();
    	for (char c : copyOf) {
    		str.append(c);
    	}
    	return str.toString();
    }
}
