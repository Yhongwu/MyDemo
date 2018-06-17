package com.howard.demo.multithreading.exercise06.parallelassemblyline;

/**
 * 并行流水线
 * 计算((A+B)*A)/2
 * 如果只是计算一条这样的式子，则即使使用多线程也无法提高效率。
 * 因为*A的计算依赖与A+B的结果，即后面的计算都依赖与前面的结果
 *
 * 但如果是要计算多条这样的式子，则可以利用多线程来并行计算。
 * 让不同的线程专一执行一个计算
 * 线程1 2 3
 * A+B
 * A+B *A
 * A+B *A /2  ---->得到结果
 * 即一开始不会有结果出来，当三个线程都装配完毕，则接下来每一次执行都会有一条式子计算结果出来。
 * 即开始三个线程 分别专一计算加 减 除
 */
public class Main {
    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        //模拟多个式子需要计算
        for (int i = 1; i <= 1000; i ++) {
            for (int j = 1; j <= 1000; j ++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.orgStr = "(("+i+"+"+j+")*"+i+")/2";
                Plus.bq.add(msg);
            }
        }
    }
}
