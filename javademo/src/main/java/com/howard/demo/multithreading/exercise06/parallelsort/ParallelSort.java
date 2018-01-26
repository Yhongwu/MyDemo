package com.howard.demo.multithreading.exercise06.parallelsort;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 并行排序
 * 使用奇偶排序
 * 5 52 6 3 4
 * 偶： 5和52比较 6和3比较 4
 * 得到 5 52 3 6 4
 * 奇： 5 52和3比较 6和4比较
 * 得到 5 3 52 4 6
 * 偶： 3 5 4 52 6
 * 奇： 3 4 5 6 52
 * 偶： 3 4 5 6 52
 * 不再发生交换 但接下来是奇排序 必须执行
 * 奇： 3 4 5 6 52
 * 不再发生交换 且当前要进行的不是奇排序 排序结束
 */
public class ParallelSort {

    static int arr[];
    static int exchFlag = 1;
    static ExecutorService pool = Executors.newCachedThreadPool();

    public static int getExchFlag() {
        return exchFlag;
    }

    public static void setExchFlag(int exchFlag) {
        ParallelSort.exchFlag = exchFlag;
    }

    public static class OddEvenSortTask implements Runnable {
        int i;
        CountDownLatch latch;
        public OddEvenSortTask(int i,CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run() {
            if (arr[i] > arr[i + 1]) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i+1] = tmp;
                setExchFlag(1);
            }
            latch.countDown();
        }
    }

    /**
     * 奇偶排序
     * 并行实现
     * @throws InterruptedException
     */
    public static void pOddEvenSort() throws InterruptedException {
        int start = 0;
        while (getExchFlag() == 1 || start == 1) {
            setExchFlag(0);
            CountDownLatch latch = new CountDownLatch(arr.length/2 - (arr.length%2 == 0?start:0));
            for (int i = start; i < arr.length - 1; i += 2) {
                pool.submit(new OddEvenSortTask(i,latch));
            }
            latch.await();
            if (start == 0)
                start = 1;
            else start = 0;
        }
    }

    /**
     * 奇偶排序
     * 串行实现
     */
    public static void OddEvenSort() {
        //exchFalg标识当前迭代是否发生数据交换
        //start标记发生奇交换还是偶交换 0表示偶交换
        //如果上一次迭代发生数据交换 或者正在进行的是奇交换 循环不停止
        int exchFalg = 1;
        int start = 0;
        while(exchFalg == 1 || start == 1) {
            exchFalg = 0;
            for (int i = start; i < arr.length - 1; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i+1] = tmp;
                    exchFalg = 1;
                }
            }
            if (start == 0)
                start = 1;
            else start = 0;

        }
    }

    public static void main(String[] args) throws InterruptedException {
        arr = new int[]{2,3,6,1,9,12,10};
//        OddEvenSort();
        pOddEvenSort();
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
}
