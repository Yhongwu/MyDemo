package com.howard.zookeeper.javaapi.connection;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * javaapi方式：需引入zookeeper包
 * zookeeper连接
 * Created by Howard Yao on 2018/8/26.
 */
public class ConnectionDemo implements Watcher{

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";
    public static void main(String[] args) throws IOException {
        // 构造方法(有多个重载)：
        // public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, long sessionId, byte[] sessionPasswd, boolean canBeReadOnly)
        // connectString：连接的地址 ip:port,多个地址用逗号分隔。
        // sessionTimeout：会话超时时间
        // watcher： 事件处理器 监听
        // sessionId： 会话ID
        // sessionPasswd： 会话密钥 与sessionId一起传入来建立连接可实现之前的会话复用
        // canBeReadOnly： 标识是否支持read-only模式，默认情况下集群中超过一半的服务出现故障，则不再接受请求。但某些情况下，希望继续提供读服务


        // zookeeper的连接过程是异步的 所以建立连接后，不代表处于已连接状态
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 5000, new ConnectionDemo());
        // 此时打印出的很可能是CONNECTING 即此时会话生命周期处于正在连接阶段
        System.out.println(zooKeeper.getState());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper连接成功");
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("watched event:" + watchedEvent);
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            // SyncConnected
            System.out.println(watchedEvent.getState());
            // 确认处于已连接的状态 才让countDownLatch减1 解除等待阻塞
            countDownLatch.countDown();
        }
    }
}
