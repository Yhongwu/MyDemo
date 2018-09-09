package com.howard.zookeeper.curator.recipes.barrier;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * DistributedDoubleBarrier 分布式barrier
 * 类似barrier
 * 达到10个后(选手全部准备好就位后)触发 leave也是 全部选手跑完后才触发
 * Created by Howard Yao on 2018/9/8.
 */
public class BarrierDemo {
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";
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
                        DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(zk, path, 10);
                        System.out.println(Thread.currentThread().getName() + " 选手就位");
                        barrier.enter();
                        System.out.println("开始...");
                        TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
                        barrier.leave();
                        System.out.println("结束...");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }


    }
}
