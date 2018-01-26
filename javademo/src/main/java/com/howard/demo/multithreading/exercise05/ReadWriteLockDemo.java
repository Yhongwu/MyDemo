package com.howard.demo.multithreading.exercise05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock
 * 读写分离
 * 运行后发现写操作串行
 * 读操作并行
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    private int value;

    public Object handleRead(Lock lock) {

        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read"+Thread.currentThread().getId());
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return 0;
    }

    public void handleWrite(Lock lock,int index) {

        try {
            lock.lock();;
            Thread.sleep(1000);
            System.out.println("write"+Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
          lock.unlock();
        }

    }

    public static void main(String[] args) {
        final  ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunable = new Runnable() {
            @Override
            public void run() {
                demo.handleRead(readLock);
//                demo.handleRead(lock);
            }
        };

        Runnable writeRunable = new Runnable() {
            @Override
            public void run() {
                demo.handleWrite(writeLock,10);
//                demo.handleWrite(lock,10);
            }
        };

        for (int i = 0 ; i < 20 ; i++) {
            new Thread(writeRunable).start();
        }

        for (int i = 0; i < 20; i ++) {
            new Thread(readRunable).start();
        }
    }

}
