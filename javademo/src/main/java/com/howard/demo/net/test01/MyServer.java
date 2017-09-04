package com.howard.demo.net.test01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 现分别有一个通过socket发送文件的客户端以及一个通过socket接收文件的服务端：
 * a) 客户端从/tmp/src.data文件中读取文件内容，通过网络socket将文件内容发给服务端
 * b) 服务端监听10000端口，当10000端口接收到客户端连接请求时，从连接读取文件内容，并写入/tmp/dst.data中

 * 1. 试写出客户端和服务端的具体实现代码（15分）
 * 2. 如果客户端需要知道服务端已经完全接收到所有文件数据并成功写入/tmp/dst.data，有何实现方式？请说出你的思路（5分）
 * 欢聚yy 2017校招笔试c卷
 * 2017年9月2日
 * @author hongwu
 */
public class MyServer {
	public void receiveFile() {
		Socket socket = null;
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		//定义缓冲区
		byte[] buffer = new byte[1024];
		
		try {
			ServerSocket serverSocket = new ServerSocket(10002);
			String path = this.getClass().getResource("/").getPath();
			System.out.println(path);
			socket = serverSocket.accept();
			inputStream = socket.getInputStream();
			outputStream = new FileOutputStream(path+"com/howard/demo/net/test01/dst.data");
			int length;
			while((length = inputStream.read(buffer, 0, buffer.length)) > 0) {
				outputStream.write(buffer, 0, length);
				outputStream.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (socket != null && socket.isConnected()) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		MyServer myServer = new MyServer();
		myServer.receiveFile();
	}
}
