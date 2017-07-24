package com.howard.stringutil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 * 字符串的压缩与解压
 * @author Howard
 * 2017年3月1日
 */
public class ZipStrUtil {
	private static final String GBK = "GBK";
	private static final String ISO_8859_1 = "ISO-8859-1";
	private static final String UTF_8 = "UTF-8";
	/**
	 * 压缩字符串
	 * @param str 原字符串
	 * @return 压缩后的字符串
	 * @throws IOException
	 */
	public static String compress(String str) throws IOException{
		
		if (null == str || str.equals("")) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes());
		gzip.close();
		return out.toString(ISO_8859_1);
	}
	/**
	 * 解压字符串
	 * @param str 待解压字符串
	 * @param coding 编码格式
	 * @return 解压后的字符串
	 * @throws IOException
	 */
	public static String unCompress(String str,String coding) throws IOException{
		if (null == str || str.equals("")){
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
		GZIPInputStream gzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n = 0;
		while((n = gzip.read(buffer))>=0){
			out.write(buffer,0,n);
		}
		if (null != coding && coding.trim().length()>0){
			return out.toString(coding);
		}
		return out.toString(UTF_8);
	}
	/**
	 * 解压字符串
	 * @param str 待解压字符串
	 * @return 解压后的字符串
	 * @throws IOException
	 */
	public static String unCompress(String str) throws IOException{
		if (null == str || str.equals("")){
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
		GZIPInputStream gzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n = 0;
		while((n = gzip.read(buffer))>=0){
			out.write(buffer,0,n);
		}
		return out.toString(UTF_8);
	}
	
	public static void main(String[] args) throws IOException {
		String str = "123456789asdfghjkk@!#$%^^&**()中文";
		System.out.println("原字符串："+str+" 长度："+str.length());
		String compress = compress(str);
		System.out.println("压缩后字符串："+compress+" 长度："+compress.length());
		String unCompress = unCompress(compress,null);
		System.out.println("解压后字符串："+unCompress+" 长度："+unCompress.length());
	}
}
