package com.howard.demo.multithreading.exercise06.parallelassemblyline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 乘法 在加法之后进行
 * 利用LinkedBlockingDeque共享数据
 */
public class Multiply implements Runnable {

    public static BlockingQueue<Msg> bq = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        while (true) {
            Msg msg = null;
            try {
                msg = bq.take();
                msg.i = msg.i * msg.j;
                Div.bq.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
