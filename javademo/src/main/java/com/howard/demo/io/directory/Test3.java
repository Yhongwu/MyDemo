package com.howard.demo.io.directory;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
/**
 * 使用no.2实现列出文件夹下所有的文件
 * 2017年10月15日
 * @author hongwu
 */
public class Test3 {
    public static void main(String[] args) throws IOException {
        Path initPath = Paths.get("H:\\备份");
        Files.walkFileTree(initPath, new SimpleFileVisitor<Path>() {
 
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) 
                    throws IOException {
                System.out.println(file.getFileName().toString());
                return FileVisitResult.CONTINUE;
            }
 
        });
    }
}
