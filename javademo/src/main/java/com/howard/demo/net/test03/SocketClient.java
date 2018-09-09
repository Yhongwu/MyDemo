package com.howard.demo.net.test03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Howard Yao on 2018/8/5.
 */
public class SocketClient {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter printWriter = null;
        BufferedReader reader = null;

        try {
            socket = new Socket("localhost",2222);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("请求连接服务端...");
            printWriter.println("准备传输数据...");
            printWriter.println("传输完毕...");
            printWriter.flush();
            socket.shutdownOutput();

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String tmp = null;
            StringBuilder info = new StringBuilder();
            while ((tmp = reader.readLine()) != null) {
                info.append(tmp);
            }
            System.out.println("客户端接收到服务端的消息: " + info.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
