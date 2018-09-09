package com.howard.zookeeper.curator.recipes.eventwatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * PathChildrenCache 对子节点变更进行监听
 * 只能监听直接子节点 对节点的节点无法监听 对本身节点的变更也无法监听
 * Created by Howard Yao on 2018/9/8.
 */
public class PathChildrenCacheDemo {

    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";

    public static void main(String[] args) throws Exception {
        String path = "/curator-test/nodecache1";

        CuratorFramework zk = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();

        zk.start();
        /**
         * 设置为false则无法获取子节点数据内容的变更情况
         */
        PathChildrenCache cache = new PathChildrenCache(zk, path, true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                switch (pathChildrenCacheEvent.getType()) {
                    case CHILD_ADDED:
                        // 新增子节点
                        System.out.println("CHILD_ADDED ：" + pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        // 子节点数据变更
                        System.out.println("CHILD_UPDATED : " + new String(pathChildrenCacheEvent.getData().getData()));
                        break;
                    case CHILD_REMOVED:
                        // 删除子节点
                        System.out.println("CHILD_REMOVED : " + pathChildrenCacheEvent.getData().getPath());
                        break;
                    default:
                        break;

                }
            }
        });

        zk.create().withMode(CreateMode.PERSISTENT).forPath(path);
        TimeUnit.SECONDS.sleep(5);

        zk.create().withMode(CreateMode.PERSISTENT)
                .forPath(path + "/aa");
        TimeUnit.SECONDS.sleep(5);

        zk.setData().forPath(path + "/aa", "666".getBytes());
        TimeUnit.SECONDS.sleep(5);
        zk.delete().forPath(path + "/aa");
        TimeUnit.SECONDS.sleep(5);
        zk.delete().forPath(path);
        TimeUnit.SECONDS.sleep(5);
    }
}
