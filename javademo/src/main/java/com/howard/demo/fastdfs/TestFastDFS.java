package com.howard.demo.fastdfs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * 测试FastDFS
 * 前提：导入相应fastdfs_client包
 * 搭建linux的FasDFS服务器
 * 注意：linux必须开放22122 23000端口
 * @author hongwu
 *
 */
public class TestFastDFS {
	public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
		// 1、把FastDFS提供的jar包添加到工程中
		// 2、初始化全局配置。加载一个配置文件。
		String path = TestFastDFS.class.getResource("/").getPath();
		ClientGlobal.init(path+"com/howard/demo/fastdfs/client.conf");
		// 3、创建一个TrackerClient对象。
		TrackerClient trackerClient = new TrackerClient();
		// 4、创建一个TrackerServer对象。
		TrackerServer trackerServer = trackerClient.getConnection();
		// 5、声明一个StorageServer对象，null。
		StorageServer storageServer = null;
		// 6、获得StorageClient对象。
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		// 7、直接调用StorageClient对象方法上传文件即可。
		String[] strings = storageClient.upload_file(path+"com/howard/demo/fastdfs/01.jpg", "jpg", null);
		for (String string : strings) {
			System.out.println(string);
		}

	}
}
