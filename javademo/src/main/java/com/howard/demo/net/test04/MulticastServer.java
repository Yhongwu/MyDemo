package com.howard.demo.net.test04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * Multicast组播
 * 多个客户端可以同时收到信息
 * Created by Howard Yao on 2018/8/5.
 */
public class MulticastServer {
    public static void main(String[] args) {
        MulticastSocket socket = null;
        try {
            //必须在该地址段：224.0.0.0 - 239.255.255.255
            InetAddress group = InetAddress.getByName("224.1.1.2");
            socket = new MulticastSocket();

            for (int i = 0 ; i < 10; i ++ ) {
                String data = "hello: " + i;
                byte[] bytes = data.getBytes();
                socket.send(new DatagramPacket(bytes,bytes.length,group,2222));

                // 源码：TimeUnit.SECONDS 较好的设计方式
                TimeUnit.SECONDS.sleep(1);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

    }
}
