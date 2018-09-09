package com.howard.zookeeper.curator.recipes.master;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * master选举
 * LeaderSelector: 封装了和master选举相关的逻辑
 * 选择一个根节点，多台机器同时向该节点创建一个子节点，只有一台可以创建成功，创建成功的则为master
 * 执行完takeLeadership这个回调方法后 会释放该master 重新进入选举过程
 * 创建的子节点是临时节点
 * Created by Howard Yao on 2018/9/8.
 */
public class MasterSelectDemo {
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";

    public static void main(String[] args) throws InterruptedException {
        String path = "/curator-master-test";

        CuratorFramework zk = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();

        zk.start();

        LeaderSelector selector = new LeaderSelector(zk, path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("经过选举成为imaster");
                // 模拟其它业务操作
                TimeUnit.SECONDS.sleep(5);
                System.out.println("释放master，重新开始选举过程");
            }
        });

        selector.autoRequeue();
        selector.start();
        TimeUnit.SECONDS.sleep(30);
    }

}
