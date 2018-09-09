package com.howard.zookeeper.curator.recipes.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 分布式锁
 * InterProcessMutex 封装了加锁和释放锁的操作
 * Created by Howard Yao on 2018/9/8.
 */
public class ZkLockDemo {
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";

    public static void main(String[] args) {
        String path = "/curator-lock-test";

        CuratorFramework zk = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();

        zk.start();

        InterProcessMutex lock = new InterProcessMutex(zk, path);
        final CountDownLatch downLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        downLatch.await();
                        // 加锁
                        lock.acquire();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss SSS");
                    String format = df.format(new Date());
                    System.out.println(format);
                    // 释放锁
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        downLatch.countDown();
    }


}
