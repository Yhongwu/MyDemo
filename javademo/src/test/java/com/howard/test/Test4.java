package com.howard.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 递归复制文件夹
 * 2017年10月10日
 * @author hongwu
 */
public class Test4 {
	public static void main(String[] args) {
		String inputPath = "G:\\beifen\\upload\\linshi";
		String outputPath = "G:\\beifen\\upload\\test2";
		Copy copy = new Copy(inputPath, outputPath);
	}

}
class Copy{
	private String inputPath;
	private String outputPath;
	public Copy(String inputPath,String outputPath) {
		super();
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		doCopy(this.inputPath, this.outputPath);
	}
	
	public void doCopy(String input,String output) {
		File inputFile = new File(input);
		File outputFile = new File(output);
		if (inputFile.isDirectory()) {
			//如果当前待复制路径是一个文件夹 则在目标路径建立新文件夹
			outputFile = new File(outputFile.getPath() + File.separator + inputFile.getName());
			outputFile.mkdirs();
			String[] fileArray = inputFile.list();
			for (String s:fileArray) {
				System.out.println("----->"+s);
			}
			for (String newFile:fileArray) {
				//递归复制文件（夹）
				doCopy(inputFile.getPath() + File.separator + newFile, outputFile.getPath());
			}
			
		}else {
			//如果当前路径是一个文件
			File temp = new File(outputFile.getPath() + File.separator + inputFile.getName());
			try {
				//新建文件
				temp.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			copyFile(inputFile, temp);
			System.out.println("inputFile路径:" + inputFile.getPath());  
            System.out.println("outputFile路径:" + outputFile.getPath()); 
		}
	}
	/**
	 * 复制文件的具体方法
	 * @param inputFile
	 * @param outputFile
	 */
	public void copyFile(File inputFile,File outputFile) {
		int b = 0;
		try (InputStream in = new FileInputStream(inputFile);
			 OutputStream out = new FileOutputStream(outputFile)){
			
			byte[] buffer = new byte[1024];
			while ((b = in.read(buffer)) != -1) {
				out.write(buffer, 0, b);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}