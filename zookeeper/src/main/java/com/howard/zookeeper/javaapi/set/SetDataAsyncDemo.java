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
public class SetDataAsyncDemo implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";
    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        // zookeeper的连接过程是异步的 所以建立连接后，不代表处于已连接状态
        zooKeeper = new ZooKeeper(connectString, 5000, new SetDataAsyncDemo());
        countDownLatch.await();

        // 创建子节点的前提是父节点必须是持久化的
        zooKeeper.create("/set-data-test1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.setData("/set-data-test1", "456".getBytes(), -1, new MySetDataCallBack(), null);

        TimeUnit.SECONDS.sleep(30);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("watched event:" + watchedEvent);
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }

}
