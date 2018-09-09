package com.howard.zookeeper.javaapi.delete;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 异步删除节点
 * 不支持递归删除，删除的节点必须没有子节点
 * Created by Howard Yao on 2018/8/26.
 */
public class DeleteCallBackDemo implements Watcher{

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 构造方法(有多个重载)：
        // public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, long sessionId, byte[] sessionPasswd, boolean canBeReadOnly)
        // connectString：连接的地址 ip:port,多个地址用逗号分隔。
        // sessionTimeout：会话超时时间
        // watcher： 事件处理器 监听
        // sessionId： 会话ID
        // sessionPasswd： 会话密钥 与sessionId一起传入来建立连接可实现之前的会话复用
        // canBeReadOnly： 标识是否支持read-only模式，默认情况下集群中超过一半的服务出现故障，则不再接受请求。但某些情况下，希望继续提供读服务


        // zookeeper的连接过程是异步的 所以建立连接后，不代表处于已连接状态
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 5000, new DeleteCallBackDemo());
        countDownLatch.await();

        // 使用同步方式创建持久化节点
        zooKeeper.create("/delete-test", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 异步删除上面创建的节点
        zooKeeper.delete("/delete-test", -1, new MyDeleteCallBack(), "delete test");

        // 为看到异步删除的结果 主线程睡眠一会儿
        TimeUnit.SECONDS.sleep(30);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("watched event:" + watchedEvent);
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            // 确认处于已连接的状态 才让countDownLatch减1 解除等待阻塞
            countDownLatch.countDown();
        }
    }
}
