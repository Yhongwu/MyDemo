package com.howard.zookeeper.curator.recipes.count;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**
 * 分布式计数器
 * DistributedAtomicInteger
 * 类似AtomicInteger DistributedAtomicInteger是在分布式环境下的原子整型
 * Created by Howard Yao on 2018/9/8.
 */
public class CountDemo {
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";

    public static void main(String[] args) throws Exception {
        String path = "/curator-count-test";

        CuratorFramework zk = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();
        zk.start();

        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(zk, path, new RetryNTimes(3, 1000));
        AtomicValue<Integer> atomicValue = atomicInteger.add(8);
        System.out.println(atomicValue.succeeded());
    }
}
