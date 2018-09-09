package com.howard.demo.net.test03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket编程demo
 * Created by Howard Yao on 2018/8/5.
 */
public class SocketServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        BufferedReader reader = null;
        PrintWriter printWriter = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(2222);
            socket = serverSocket.accept();
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String tmp = null;
            StringBuilder info = new StringBuilder();
            while ((tmp = reader.readLine()) != null) {
                info.append(tmp+"\n");
            }
            System.out.println("服务端接收到服务端的消息: "+info.toString());

            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.print("服务端已经接受到消息");
            printWriter.flush();

            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                printWriter.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
