package com.howard.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 将指定目录下的所有.java文件复制到另一指定目录，并将原来文件的扩展名从.java改为.txt
 * listFiles可以接收一个FilenameFilter类，重写accept方法可以过滤指定文件
 * @author Howard
 * 2017年3月9日
 */
public class Jave2Txt {
	public static void main(String[] args) throws Exception {
		//源文件夹
		File srcDir = new File("E:\\upload\\test\\java");
		if (!srcDir.exists() || !srcDir.isDirectory())
			throw new Exception("目录不存在");
		//过滤非.java结尾的文件
		File[] files = srcDir.listFiles(
				new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						return name.endsWith(".java");
					}
				});
		System.out.println(files.length);
		//目标文件夹
		File destDir = new File("E:\\upload\\test\\txt");
		//不存在则创建
		if (!destDir.exists()) 
			destDir.mkdirs();
		//正则表达式改变后缀名
		for(File f:files) {
			FileInputStream input = new FileInputStream(f);
			String destFileName = f.getName().replaceAll("\\.java$", ".txt");
			FileOutputStream output = new FileOutputStream(new File(destDir, destFileName));
			copy(input, output);
			input.close();
			output.close();
		}
	}
	/**
	 * 复制文件方法
	 * 参数尽量抽象 尽量用父类，所以这里用InputStream而不是FileInputStream
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void copy(InputStream in,OutputStream out) throws IOException {
		int len = 0;
		byte[] buf = new byte[1024];
		while((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
	}
	
}	
