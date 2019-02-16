package com.howard.demo.io.directory;

import java.io.File;
/**
 * 递归列出某文件夹下所有的文件
 * 2017年10月15日
 * @author hongwu
 */
public class Test2 {
	public static void main(String[] args) {
        showDirectory(new File("H:\\备份"));
    }
 
    public static void showDirectory(File f) {
        _walkDirectory(f, 0);
    }
 
    private static void _walkDirectory(File f, int level) {
        if(f.isDirectory()) {
            for(File temp : f.listFiles()) {
                _walkDirectory(temp, level + 1);
            }
        } else {
            for(int i = 0; i < level - 1; i++) {
                System.out.print("\t");
            }
            System.out.println(f.getName());
        }
    }
}
