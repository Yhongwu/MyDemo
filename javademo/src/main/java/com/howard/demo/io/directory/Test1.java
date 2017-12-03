package com.howard.demo.io.directory;

import java.io.File;
/**
 * 列出某文件夹下的文件(只列出这一级目录下的)
 * 2017年10月15日
 * @author hongwu
 */
public class Test1 {
    public static void main(String[] args) {
        File f = new File("I:\\");
        for(File temp : f.listFiles()) {
            if(temp.isFile()) {
                System.out.println(temp.getName());
            }
        }
    }
}
