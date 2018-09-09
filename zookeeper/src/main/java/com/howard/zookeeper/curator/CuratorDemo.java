package com.howard.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.TimeUnit;

/**
 * curator 是开源zookeeper客户端
 * 同步方式
 * Created by Howard Yao on 2018/9/1.
 */
public class CuratorDemo {
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";

    public static void main(String[] args) throws Exception {
        //getConnection();
        //getConnectionFluent();
        //createNode();
        //deleteNode();
        //getStat();
        updateNode();
    }

    /**
     * 创建会话
     * 不同于javaapi zkClient，curator建立会话通过CuratorFrameworkFactory工厂来创建，并需要调用start来启动
     * @throws InterruptedException
     */
    public static void getConnection() throws InterruptedException {
        // 重试策略
        // public ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries, int maxSleepMs)
        // baseSleepTimeMs: 初始sleep时间
        // maxRetries： 最大重试次数
        // maxSleepMs： 最大sleep时间
        // 当前需要sleep时间为：
        // long sleepMs = (long)(this.baseSleepTimeMs * Math.max(1, this.random.nextInt(1 << retryCount + 1)));
        // sleep时间会越来越大 maxRetries则限制不会无限次重试
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 3);

        // public static CuratorFramework newClient(String connectString, int sessionTimeoutMs, int connectionTimeoutMs, RetryPolicy retryPolicy)
        // connectString 服务器列表
        // sessionTimeoutMs session超时时间，默认60000
        // connectionTimeoutMs 创建连接超时时间
        // retryPolicy 重试策略 默认提供4种实现
        // retryPolicy中有一个接口allowRetry
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, 5000, 3000, exponentialBackoffRetry);
        client.start();
        System.out.println("zookeeper 连接已建立");
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * Fluent风格创建会话
     * @throws InterruptedException
     */
    public static void getConnectionFluent() throws InterruptedException {
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(exponentialBackoffRetry)
                .namespace("zk-curator")   // 隔离命名空间 即后面默认都是加了/zk-curator 前提是该节点必须存在
                .build();
        client.start();
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 创建节点
     * @throws Exception
     */
    public static void createNode() throws Exception {
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(exponentialBackoffRetry)
                .build();
        client.start();

        // 创建节点
        // 若没指定类型 默认持久
        // creatingParentsIfNeeded递归创建
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)  //指定类型临时节点
                .forPath("/zk-curator/a1","123".getBytes());
    }

    public static void deleteNode() throws Exception {
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(exponentialBackoffRetry)
                .build();
        client.start();

        // deletingChildrenIfNeeded遍历子节点进行删除
        // withVersion 可以指定版本进行删除 版本号可通过stat获取
        // guaranteed 强制保证删除 只要会话有效(可能某些情况下网络问题造成删除不成功 这时guaranteed会保证只要会话有效，连接成功会继续进行删除)
        client.delete()
                .deletingChildrenIfNeeded()
                .forPath("/zk-curator");
    }

    /**
     * 获取节点信息
     * @throws Exception
     */
    public static void getStat() throws Exception {
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(exponentialBackoffRetry)
                .build();
        client.start();
        // 创建节点
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)  //指定类型临时节点
                .forPath("/zk-curator2/a1","123".getBytes());

        // 获取信息
        Stat stat = new Stat();
        byte[] bytes = client.getData().storingStatIn(stat).forPath("/zk-curator2/a1");
        System.out.println(new String(bytes));
        System.out.println(stat.getVersion());
        // 获取其它信息....
    }

    public static void updateNode() throws Exception {
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(exponentialBackoffRetry)
                .build();
        client.start();

        // 创建节点
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)  //指定类型临时节点
                .forPath("/zk-curator2/a1","123".getBytes());

        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/zk-curator2/a1");
        System.out.println("当前版本号：" + stat.getVersion());

        // 更新
        client.setData().forPath("/zk-curator2/a1", "456".getBytes());
        client.getData().storingStatIn(stat).forPath("/zk-curator2/a1");
        System.out.println("当前版本号：" + stat.getVersion());
        // 基于version更新
        client.setData().withVersion(stat.getVersion()).forPath("/zk-curator2/a1", "456".getBytes());
        Stat stat2 = new Stat();
        client.getData().storingStatIn(stat2).forPath("/zk-curator2/a1");
        System.out.println("当前版本号：" + stat2.getVersion());

        //尝试更新旧版本号
        try {
            client.setData().withVersion(stat.getVersion()).forPath("/zk-curator2/a1", "456".getBytes());
        }catch (Exception e) {
            System.out.println("更新失败： "+ e.getMessage());
        }
    }
}
