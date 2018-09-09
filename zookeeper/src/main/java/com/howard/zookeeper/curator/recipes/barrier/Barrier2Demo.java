package com.howard.zookeeper.curator.recipes.barrier;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * DistributedBarrier 分布式barrier
 * 类似barrier
 * Created by Howard Yao on 2018/9/8.
 */
public class Barrier2Demo {
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";
    private static DistributedBarrier barrier;
    public static void main(String[] args) throws Exception {

        String path = "/curator-barrier-test";

        for (int i = 0; i < 10; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework zk = CuratorFrameworkFactory.builder()
                                .connectString(connectString)
                                .sessionTimeoutMs(5000)
                                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                                .build();
                        zk.start();
                        barrier = new DistributedBarrier(zk, path);
                        System.out.println(Thread.currentThread().getName() + " 选手就位");
                        barrier.setBarrier();
                        // 等待barrier移除
                        barrier.waitOnBarrier();
                        System.out.println("开始...");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        // 模拟等待所有线程启动
        TimeUnit.SECONDS.sleep(10);
        // 移除barrier
        barrier.removeBarrier();

    }
}
