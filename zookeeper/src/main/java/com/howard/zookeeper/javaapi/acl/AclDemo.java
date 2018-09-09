package com.howard.zookeeper.javaapi.acl;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * acl权限控制
 * Created by Howard Yao on 2018/8/26.
 */
public class AclDemo implements Watcher{

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        ZooKeeper z1 = new ZooKeeper(connectString, 5000, new AclDemo());
        countDownLatch.await();
        // 添加权限信息
        // addAuthInfo(String scheme, byte auth[])
        // scheme：权限控制模式：world、auth、digest、ip、super
        // auth：具体的权限信息
        z1.addAuthInfo("digest","root:123".getBytes());
        // 创建节点的时候，选择CREATOR_ALL_ACL
        z1.create("/acl-test", "123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        z1.create("/acl-test/a1", "456".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);

        // 权限信息正确 正常访问
        ZooKeeper z2 = new ZooKeeper(connectString, 5000, new AclDemo());
        countDownLatch.await();
        z2.addAuthInfo("digest","root:123".getBytes());
        z2.getData("/acl-test", false, null);

        // 没有权限信息 访问出错
        ZooKeeper z3 = new ZooKeeper(connectString, 5000, new AclDemo());
        countDownLatch.await();
        try {
            z3.getData("/acl-test", false, null);
        }catch (Exception e) {
            System.out.println("z3访问失败：" + e.getMessage());
        }

        // 权限信息错误
        ZooKeeper z4 = new ZooKeeper(connectString, 5000, new AclDemo());
        z4.addAuthInfo("digest","root:321".getBytes());
        countDownLatch.await();
        try {
            z4.getData("/acl-test", false, null);
        }catch (Exception e) {
            System.out.println("z4访问失败：" + e.getMessage());
        }

        // 设置的权限信息最终只对子节点有效，所以删除子节点需要权限 而父节点不需要
        //没有附带权限信息 删除子节点
        ZooKeeper z5 = new ZooKeeper(connectString, 5000, new AclDemo());
        countDownLatch.await();
        try {
            z5.delete("/acl-test/a1", -1);
        }catch (Exception e) {
            System.out.println("z5删除节点失败");
        }

        // 带上权限信息删除子节点
        ZooKeeper z6 = new ZooKeeper(connectString, 5000, new AclDemo());
        countDownLatch.await();
        z6.addAuthInfo("digest","root:123".getBytes());
        try {
            z6.delete("/acl-test/a1", -1);
        }catch (Exception e) {
            System.out.println("z6删除节点失败");
        }

        // 删除父节点
        ZooKeeper z7 = new ZooKeeper(connectString, 5000, new AclDemo());
        countDownLatch.await();
        try {
            z7.delete("/acl-test", -1);
        }catch (Exception e) {
            System.out.println("z7删除节点失败");
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("watched event:" + watchedEvent);
        if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
            // 确认处于已连接的状态 才让countDownLatch减1 解除等待阻塞
            countDownLatch.countDown();
        }
    }
}
