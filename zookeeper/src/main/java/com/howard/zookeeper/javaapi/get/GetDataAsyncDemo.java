package com.howard.zookeeper.javaapi.get;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 获取数据节点详细信息
 * 异步方式
 * Created by Howard Yao on 2018/8/26.
 */
public class GetDataAsyncDemo implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";
    private static ZooKeeper zooKeeper = null;
    private static Stat stat = new Stat();
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        // zookeeper的连接过程是异步的 所以建立连接后，不代表处于已连接状态
        zooKeeper = new ZooKeeper(connectString, 5000, new GetDataAsyncDemo());
        countDownLatch.await();

        // 创建子节点的前提是父节点必须是持久化的
        zooKeeper.create("/get-data-test1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //传入一个stat用来接受最新的stat信息 true注册watch 接受事件通知
        zooKeeper.getData("/get-data-test1", true, new MyGetDataCallBack(), null);


        // Version 0 -> 1
        zooKeeper.setData("/get-data-test1", "123".getBytes(), -1);

        TimeUnit.SECONDS.sleep(30);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("watched event:" + watchedEvent);
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                // 节点数据改变通知
                //传入一个stat用来接受最新的stat信息 true注册watch 接受事件通知
                zooKeeper.getData("/get-data-test1", true, new MyGetDataCallBack(), null);
            }

        }
    }
}
