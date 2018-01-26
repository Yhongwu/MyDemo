package com.howard.demo.multithreading.exercise05;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量
 */
public class SemapDemo implements Runnable{

    /**
     * Semaphore 信号量，表示最多同时5个线程进入临界区代码段
     */
    final Semaphore semp = new Semaphore(5);

    @Override
    public void run() {
        try {
            //申请许可
            semp.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+ ":done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放
            semp.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for (int i = 0 ; i < 20; i++) {
            executorService.submit(demo);
        }
    }
}
