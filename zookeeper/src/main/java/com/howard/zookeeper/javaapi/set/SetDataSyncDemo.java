package com.howard.zookeeper.javaapi.set;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 设置数据节点内容
 * 同步方式
 * Created by Howard Yao on 2018/8/26.
 */
public class SetDataSyncDemo implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";
    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        // zookeeper的连接过程是异步的 所以建立连接后，不代表处于已连接状态
        zooKeeper = new ZooKeeper(connectString, 5000, new SetDataSyncDemo());
        countDownLatch.await();

        // 创建子节点的前提是父节点必须是持久化的
        zooKeeper.create("/set-data-test1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.getData("/set-data-test1", true, null);

        // 第一次更新 -1表示不需要原子性更新
        Stat stat = zooKeeper.setData("/set-data-test1", "456".getBytes(), -1);
        printhInfo(stat);

        // 第二次更新 期望上个版本是stat.getVersion
        Stat stat2 = zooKeeper.setData("/set-data-test1", "789".getBytes(), stat.getVersion());
        printhInfo(stat2);

        // 第三次更新 期望上个版本是stat.getVersion
        // 但此时版本号已经为stat2.getVersion，不满足原子性了(cap)
        try {
            zooKeeper.setData("/set-data-test1", "789".getBytes(), stat.getVersion());
        } catch (KeeperException e) {
            System.out.println("error: " + e.getCode() + ","+ e.getMessage()+ "," + e.getPath());
        }

        TimeUnit.SECONDS.sleep(30);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("watched event:" + watchedEvent);
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }

    private static void printhInfo(Stat stat) {
        System.out.println("打印信息...");
        System.out.println("cVersion: "+ stat.getCversion());
        System.out.println("Mzxid: "+ stat.getMzxid());
        System.out.println("Version: " + stat.getVersion());
        System.out.println("Czxid:" + stat.getCzxid());
        System.out.println("打印信息结束...");
    }
}
