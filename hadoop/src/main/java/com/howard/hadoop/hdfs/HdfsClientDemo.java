package com.howard.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * hdfs 客户端
 * 需要使用hast工具配置centos-114映射为ip
 * Created by Howard Yao on 2018/10/6.
 */
public class HdfsClientDemo {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {


    }

    /**
     * 上传文件
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test
    public void uploadFile() throws IOException {
        FileSystem fileSystem = getFileSystem();
        fileSystem.copyFromLocalFile(new Path("G:\\tmp\\hdfs-test2.txt"), new Path("/hdfs-test2.txt"));
        fileSystem.close();
    }

    /**
     * 文件下载
     * @throws IOException
     */
    @Test
    public void downloadFile() throws IOException {
        FileSystem fileSystem = getFileSystem();
        System.out.println(fileSystem == null);
        /**
         * delSrc: 指是否将原文件删除
         * useRawLocalFileSystem :最后一个参数表示不用原始的本地文件系统，改用java的io流
         * 加上这两个参数 防止报NPE
         */
        fileSystem.copyToLocalFile(false, new Path("/hdfs-test.txt"),
                new Path("G:/tmp/hdfs-download1.txt"), true);
        fileSystem.close();
    }

    /**
     * 创建文件夹
     * @throws IOException
     */
    @Test
    public void mkdirAtHDFS() throws IOException {
        FileSystem fileSystem = getFileSystem();
        // 创建目录
        // 可以创建多级目录
        fileSystem.mkdirs(new Path("/yhw/test/test01"));
        fileSystem.close();
    }

    /**
     * 删除文件夹
     * @throws IOException
     */
    @Test
    public void deleteAtHDFS() throws IOException {
        FileSystem fileSystem = getFileSystem();
        // 该方法已过时
        //fileSystem.delete(new Path("/yhw/test"));
        // 删除文件夹 ，如果是非空文件夹，参数2必须给值true
        fileSystem.delete(new Path("/yhw/test"), true);
        fileSystem.close();
    }

    /**
     * 重命名
     * @throws IOException
     */
    @Test
    public void renameFile() throws IOException {
        FileSystem fileSystem = getFileSystem();
        //2 重命名文件或文件夹
        fileSystem.rename(new Path("/hdfs-test2.txt"), new Path("/hdfs-test1.txt"));
        fileSystem.close();
    }

    /**
     * 查看文件详情
     * @throws IOException
     */
    @Test
    public void readListFiles() throws IOException {
        FileSystem fileSystem = getFileSystem();
        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/"), true);

        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();

            System.out.println(fileStatus.getPath().getName());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getLen());

            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            // 每块的起始点 分布的节点
            for (BlockLocation bl : blockLocations) {
                System.out.println("block-offset:" + bl.getOffset());
                String[] hosts = bl.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
        }
        fileSystem.close();
    }

    /**
     * 文件夹查看
     * @throws IOException
     */
    @Test
    public void listFileTest() throws IOException {
        FileSystem fileSystem = getFileSystem();
        // 获取查询路径下的文件状态信息
        FileStatus[] listStatus = fileSystem.listStatus(new Path("/"));
        // 遍历所有文件状态
        for (FileStatus status : listStatus) {
            if (status.isFile()) {
                // 文件
                System.out.println("f--" + status.getPath().getName());
            } else {
                // 文件夹
                System.out.println("d--" + status.getPath().getName());
            }
        }
        fileSystem.close();
    }

    /**
     * IO流的方式上传文件
     * @throws IOException
     */
    @Test
    public void uploadWithIO() throws IOException {
        FileSystem fileSystem = getFileSystem();
        Configuration configuration = new Configuration();
        // 获取输出流
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/yhw/hdfs-test3.txt"), true);
        // 获取输入流
        FileInputStream fileInputStream = new FileInputStream(new File("G:/tmp/hdfs-test3.txt"));

        try {
            IOUtils.copyBytes(fileInputStream, fsDataOutputStream, configuration, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
            fsDataOutputStream.close();
        }
        fileSystem.close();
    }

    /**
     * IO流方式下载
     * @throws IOException
     */
    @Test
    public void downloadWithIO() throws IOException {
        FileSystem fileSystem = getFileSystem();
        Configuration configuration = new Configuration();
        FSDataInputStream fsDataInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fsDataInputStream = fileSystem.open(new Path("/yhw/hdfs-test3.txt"));
            fileOutputStream = new FileOutputStream(new File("G:/tmp/hdfs-download2.txt"));
            IOUtils.copyBytes(fsDataInputStream,fileOutputStream, configuration);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileOutputStream.close();
            fsDataInputStream.close();
        }
        fileSystem.close();
    }
    @Test
    public void hdfsHello() throws IOException, URISyntaxException, InterruptedException {
        // 获取文件系统
        Configuration configuration = new Configuration();
        // 配置在集群上运行
        // centos-114需要在host文件配置对应ip 可用SwitchHosts.exe
        configuration.set("fs.defaultFS", "hdfs://centos-114:8020");
        //使用这种方式启动需要在启动时添加启动参数(配置用户名)：-DHADOOP_USER_NAME=yhw
        //FileSystem fileSystem = FileSystem.get(configuration);

        // 直接配置访问集群的路径和访问集群的用户名称
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://centos-114:8020"),configuration, "yhw");
        // 把本地文件上传到文件系统中
        fileSystem.copyFromLocalFile(new Path("G:\\tmp\\hdfs-test.txt"), new Path("/hdfs-test.txt"));

        // 关闭资源
        fileSystem.close();
        System.out.println("over");
    }

    /************以下两个为一个测试案例  定位读取下载大文件*****/
    /**
     * 下载大文件的第一块
     * @throws IOException
     */
    @Test
    public void downloadFile1() throws IOException {
        FileSystem fileSystem = getFileSystem();
        Configuration configuration = new Configuration();
        FSDataInputStream fsDataInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fsDataInputStream = fileSystem.open(new Path("/test/test01/hadoop-2.7.2.tar.gz"));
            fileOutputStream = new FileOutputStream(new File("G:/tmp/hadoop-2.7.2.tar.gz.part1"));
            // 模拟读取一块
            byte[] buf = new byte[1024];
            // 128 * 1024 *1024
            for (int i = 0 ; i < 1024 * 128; i ++ ) {
                fsDataInputStream.read(buf);
                fileOutputStream.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileOutputStream.close();
            fsDataInputStream.close();
        }
        fileSystem.close();
    }

    /**
     * 模拟读取大文件第二块(假设文件共2块)
     * 完成后 window使用cmd：type hadoop-2.7.2.tar.gz.part2 >> hadoop-2.7.2.tar.gz.part1 拼接文件
     * 然后修改hadoop-2.7.2.tar.gz.part1 为 hadoop-2.7.2.tar.gz，删除hadoop-2.7.2.tar.gz.part2 即可
     * @throws IOException
     */
    @Test
    public void downloadFile2() throws IOException {
        FileSystem fileSystem = getFileSystem();
        Configuration configuration = new Configuration();
        FSDataInputStream fsDataInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fsDataInputStream = fileSystem.open(new Path("/test/test01/hadoop-2.7.2.tar.gz"));
            fileOutputStream = new FileOutputStream(new File("G:/tmp/hadoop-2.7.2.tar.gz.part2"));

            // 流对接 定位到第二块起始位置
            fsDataInputStream.seek(1024 * 1024 * 128);

            IOUtils.copyBytes(fsDataInputStream,fileOutputStream, configuration);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileOutputStream.close();
            fsDataInputStream.close();
        }
        fileSystem.close();
    }

    /**
     * 获取文件系统
     * @return
     * @throws URISyntaxException
     */
    public static FileSystem getFileSystem(){
        // 获取文件系统
        Configuration configuration = new Configuration();
        // 配置在集群上运行
        // centos-114需要在host文件配置对应ip 可用SwitchHosts.exe
        configuration.set("fs.defaultFS", "hdfs://centos-114:8020");

        // 直接配置访问集群的路径和访问集群的用户名称
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new URI("hdfs://centos-114:8020"),configuration, "yhw");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return fileSystem;
    }

}
