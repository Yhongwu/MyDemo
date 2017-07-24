package com.howard.fileutil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * IO和NIO方式实现文件复制
 * @author Howard
 * 2017年3月4日
 */
public class FileCopyUtil {
	//工具类构造方法设为私有 不然创建 好习惯
	private FileCopyUtil(){};
	/**
	 * IO的方式实现文件复制
	 * @param source 源文件路径
	 * @param target 目标文件路径
	 * @throws IOException
	 */
	public static void fileCopyIO(String source,String target) throws IOException{
		InputStream in = new FileInputStream(source);
		OutputStream out = new FileOutputStream(target);
		
		byte[] b = new byte[1024];
		int tmp = -1;
		while ((tmp = in.read(b)) != -1) {
			out.write(b, 0, tmp);
		}
		
	}
	/**
	 * NIO方式实现文件复制
	 * @param source 源文件路径
	 * @param target 目标文件路径
	 * @throws IOException
	 */
	public static void fileCopyNIO(String source,String target) throws IOException{
		FileInputStream in = new FileInputStream(source);
		FileOutputStream out = new FileOutputStream(target);
		FileChannel fIn = in.getChannel();
		FileChannel fOut = out.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (fIn.read(buffer) != -1) {
			buffer.flip();
			fOut.write(buffer);
			buffer.clear();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		fileCopyIO("E:\\upload\\test\\copyfilesource.txt", "E:\\upload\\test\\copyfiletarget.txt");
//		String resource = FileCopyUtil.class.getResource("/").getPath();
//		System.out.println(resource);
//		InputStream source = FileCopyUtil.class.getClassLoader().getResourceAsStream("/copyfilesource.txt");
//		
//		OutputStream target = new FileOutputStream("E:\\upload\\test\\copyfiletarget2.txt");
//		fileCopyIO(source, target);
//		String path = Thread.currentThread().getContextClassLoader().getResource("copyfilesource.txt").toString();
//		path = path.substring(6);
//		String source = path + "copyfilesource.txt";
//		String target = path + "copyfiletarget.txt";
//		System.out.println(path);
//		fileCopyIO(source, target);
//				fileCopyNIO("/copyfilesource.txt", "/copyfiletarget.txt");
	}
}
