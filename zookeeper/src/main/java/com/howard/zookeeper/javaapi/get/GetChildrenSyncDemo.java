package com.howard.zookeeper.javaapi.get;

import com.howard.zookeeper.javaapi.connection.ConnectionDemo;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 获取某路径下的所有子节点
 * 同步方式
 * Created by Howard Yao on 2018/8/26.
 */
public class GetChildrenSyncDemo implements Watcher{

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";
    private static ZooKeeper zooKeeper = null;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        // zookeeper的连接过程是异步的 所以建立连接后，不代表处于已连接状态
        zooKeeper = new ZooKeeper(connectString, 5000, new GetChildrenSyncDemo());
        countDownLatch.await();

        // 创建子节点的前提是父节点必须是持久化的
        zooKeeper.create("/get-test1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create("/get-test1/g1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        // 获取/get-test1下的所有子节点 true表示注册watch，使用默认的watch
        List<String> children = zooKeeper.getChildren("/get-test1", true);
        System.out.println(children);

        /**********watch**********/
        // 上面在获取子节点的时候注册了一个watch watch注册后触发一次就得重新注册
        zooKeeper.create("/get-test1/g2", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("watched event:" + watchedEvent);
        if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                // 节点发生添加或删除触发
                try {
                    System.out.println("child node change :" + zooKeeper.getChildren(watchedEvent.getPath(), true));
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
