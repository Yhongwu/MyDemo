package com.howard.demo.other;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ListBlockingQueue<T> {
    private List<T> queue = new LinkedList<>();
    //默认初始化容量
    private int limit = 10;

    public ListBlockingQueue() {
    }

    public ListBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized  void put(T t) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            System.out.println("队列已满...");
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        System.out.println("存放值:"+t);
        this.queue.add(t);
    }

    public synchronized  T take() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.limit) {
            notifyAll();
        }
        T t = this.queue.remove(0);
        System.out.println("取出值："+t);
        return t;
    }

    //测试
    public static void main(String[] args) throws InterruptedException {
        ListBlockingQueue<Integer> queue = new ListBlockingQueue<>(4);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 15; i ++) {
                    try {
                        queue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        for (int i = 0 ; i < 15 ;i ++) {
            queue.take();
        }
    }
}
