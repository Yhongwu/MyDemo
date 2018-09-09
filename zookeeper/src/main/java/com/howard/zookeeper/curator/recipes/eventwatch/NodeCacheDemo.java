package com.howard.zookeeper.curator.recipes.eventwatch;

import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * curator对事件监听的支持
 * 能够自动处理反复监听 简化开发
 * nodecache 监听节点 节点创建和节点数据内容变化会触发 删除不会触发
 * Created by Howard Yao on 2018/9/8.
 */
public class NodeCacheDemo {

    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";

    public static void main(String[] args) throws Exception {
        String path = "/curator-test/nodecache";

        CuratorFramework zk = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();

        zk.start();

        /**
         * 客户端实例 路径 是否进行数据压缩
         */
        NodeCache nodeCache = new NodeCache(zk, path, false);
        // 可尝试将创建过程移动到建立监听后 可发现可监听节点的创建
        zk.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path,"123".getBytes());
        /**
         * 默认false
         * 设置为true 则启动时会立即从zk上读取对应节点的数据内容 并保存在zk上
         */
        nodeCache.start(false);
        // 上面设置为false则此处无法获取节点的数据内容(空指针)
        System.out.println(new String(nodeCache.getCurrentData().getData()));
        // 节点不存在 也可以进行监听 则节点创建后会触发该时间
        // 节点删除时不会触发
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                // 回调接口
                System.out.println("node data changed : " + new String(nodeCache.getCurrentData().getData()));
            }
        });

        //TimeUnit.SECONDS.sleep(5);
        zk.setData().forPath(path, "456".getBytes());
        TimeUnit.SECONDS.sleep(5);
        zk.delete().deletingChildrenIfNeeded().forPath(path);
        TimeUnit.SECONDS.sleep(5);
    }
}
