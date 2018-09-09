package com.howard.zookeeper.curator.recipes.master;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 没有加锁情况的可能出现的问题
 * Created by Howard Yao on 2018/9/8.
 */
public class NoLock {
    public static void main(String[] args) {
        final CountDownLatch downLatch = new CountDownLatch(1);

        for (int i = 0 ; i < 10; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        downLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss SSS");
                    String format = df.format(new Date());
                    System.out.println(format);
                }
            }).start();
        }
        downLatch.countDown();
    }
}
