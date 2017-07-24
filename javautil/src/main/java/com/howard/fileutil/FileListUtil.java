package com.howard.fileutil;

import java.io.File;
/**
 * 列出某路径下的所有文件
 * 待扩充
 * @author Howard
 * 2017年3月4日
 */
public class FileListUtil {
	
	private FileListUtil(){};
	/**
	 * 递归列出某路径下的所有文件
	 * @param file
	 */
	public static void fileList(File file){
		if (null != file) {
			if(file.isDirectory()){
				File[] fileArray = file.listFiles();
				if (null != fileArray) {
					for (int i = 0; i < fileArray.length; i++){
						fileList(fileArray[i]);
					}
				}
			}else{
				System.out.println(file);
			}
		}
	}
	
	public static void main(String[] args) {
		fileList(new File("E:\\upload"));
		
	}
	
}
