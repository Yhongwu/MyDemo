package com.howard.demo.net.test01;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public class MyClient {
	public void sendFile() {
		Socket socket = new Socket();
		SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 10002);
		FileInputStream inputStream = null;
		OutputStream outputStream = null;
		
		byte[] buffer = new byte[1024];
		
		try {
			String path = this.getClass().getResource("/").getPath();
			socket.connect(socketAddress);
			inputStream = new FileInputStream(path+"com/howard/demo/net/test01/src.data");
			outputStream = socket.getOutputStream();
			int length;
			while ((length = inputStream.read(buffer, 0, buffer.length)) > 0) {
				outputStream.write(buffer,0,length);
				outputStream.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		MyClient myClient = new MyClient();
		myClient.sendFile();
	}
}
