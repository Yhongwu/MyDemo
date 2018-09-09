package com.howard.demo.net.test04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * 客户端2
 * Created by Howard Yao on 2018/8/5.
 */
public class MulticastClient2 {

    public static void main(String[] args) {
        MulticastSocket socket = null;
        try {
            InetAddress group=InetAddress.getByName("224.1.1.2");
            socket=new MulticastSocket(2222);
            socket.joinGroup(group);
            byte[] bytes = new byte[256];

            while (true) {
                DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
                socket.receive(packet);

                String msg=new String(packet.getData());
                System.out.println("接收到的数据："+msg);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
