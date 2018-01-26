package com.howard.demo.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用数组模拟阻塞队列
 * @param <T>
 */
public  class ArrayBlockingQueue<T> {

    private Object[] item = new Object[4];
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private int count;
    private int startIndex;
    private int endIndex;

    public void add(T t) {
        lock.lock();
        try {
            System.out.println("存放值："+t);
            while (count == item.length) {
                try {
                    System.out.println("队列已满，阻塞...");
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            item[startIndex++] = t;
            count++;
            if (startIndex == item.length) {
                startIndex = 0;
            }
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();
        try {
            while (count == 0) {
                try {
                    System.out.println("队列空，阻塞...");
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = (T)item[endIndex++];
            System.out.println("取出值："+t);
            count --;
            if (endIndex == item.length) {
                endIndex = 0;
            }
            notFull.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 3; i ++) {
                    queue.add(i);
                }
            }
        });

        try {
            thread.join();
            thread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0 ; i < 15 ;i ++) {
            queue.take();
        }
    }

}
