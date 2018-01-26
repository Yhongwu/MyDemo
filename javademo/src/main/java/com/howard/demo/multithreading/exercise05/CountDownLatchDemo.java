package com.howard.demo.multithreading.exercise05;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 倒计时
 * 相当于跑步等人都到齐了才开始发号
 */
public class CountDownLatchDemo implements Runnable {
    static final CountDownLatchDemo demo = new CountDownLatchDemo();
    //倒计时器 表示10个线程完成后才开始
    static final CountDownLatch end = new CountDownLatch(10);
    @Override
    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0 ; i < 10; i ++ ) {
            executorService.submit(demo);
        }

        //等待10个线程完成再继续
        end.await();
        System.out.println("go");
        executorService.shutdown();

    }
}
