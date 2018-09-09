package com.howard.zookeeper.javaapi.exists;

import com.howard.zookeeper.javaapi.get.MyGetChildrenCallBack;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 检测是否存在节点
 * 检测的同时可以注册watch
 * Created by Howard Yao on 2018/8/26.
 */
public class ExistsDemo implements Watcher{

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";
    private static ZooKeeper zooKeeper = null;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        // zookeeper的连接过程是异步的 所以建立连接后，不代表处于已连接状态
        zooKeeper = new ZooKeeper(connectString, 5000, new ExistsDemo());
        countDownLatch.await();

        zooKeeper.exists("/exists-test", true);

        // 创建子节点的前提是父节点必须是持久化的
        zooKeeper.create("/exists-test", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.setData("/exists-test", "456".getBytes(), -1);

        zooKeeper.create("/exists-test/e1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.delete("/exists-test/e1", -1);
        zooKeeper.delete("/exists-test", -1);

        TimeUnit.SECONDS.sleep(30);


    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("watched event:" + watchedEvent);
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            try {
                if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                    countDownLatch.countDown();
                } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                    System.out.println(watchedEvent.getPath() + " deleted");
                    //重新注册事件通知
                    zooKeeper.exists(watchedEvent.getPath(), true);
                } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {
                    System.out.println(watchedEvent.getPath() + " created");
                    zooKeeper.exists(watchedEvent.getPath(), true);
                } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                    System.out.println(watchedEvent.getPath() + " changed");
                    zooKeeper.exists(watchedEvent.getPath(), true);
                }
            }catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
