package com.howard.common;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

/**
 * 将a文件和b文件中的单词交替合并
 * a文件单词用空格隔开
 * b文件单词使用空格或者回车隔开
 * @author Howard
 * 2017年3月9日
 */
public class MergeFiles {
	public static void main(String[] args) throws Exception {
		FileManager a = new FileManager("E:\\upload\\test\\a.txt",new char[]{' '});
		FileManager b = new FileManager("E:\\upload\\test\\b.txt",new char[]{'\n',' '});
		
		FileWriter c = new FileWriter("E:\\upload\\test\\c.txt");
		String aWord = null;
		String bWord = null;
		
		while ((aWord = a.nextWord()) != null) {
			c.write(aWord + " ");
			bWord = b.nextWord();
			if (bWord != null) {
				c.write(bWord + " ");
			}
		}
		
		while ((bWord = b.nextWord()) != null) {
			c.write(bWord + " ");
		}
		c.close();
	}
	
	
}
class FileManager {
	String[] words = null;
	int pos = 0;
	public FileManager(String filename,char[] seperators) throws Exception {
		File f = new File(filename);
		int len = 0;
		FileReader reader = new FileReader(f);
		char[] buf = new char[1024];
		//读取文件到字符串里
		StringBuilder str = new StringBuilder();
		while ((len = reader.read(buf))!=-1) {
			str.append(buf, 0, len);
		}
		reader.close();
		
		String s = str.toString();
		String regex = null;
		if (seperators.length > 1) {
			regex = "" + seperators[0] + "|" +seperators[1];
		}else {
			regex = "" + seperators[0];
		}
		words = s.split(regex);
		System.out.println(Arrays.toString(words));
	}
	
	public String nextWord() {
		if (pos == words.length)
			return null;
		return words[pos++];
	}
}