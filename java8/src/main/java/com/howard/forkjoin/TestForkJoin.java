package com.howard.forkjoin;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * Created by Howard Yao on 2018/6/5.
 */
public class TestForkJoin {

    /**
     * 测试java8之前已有的forkjoin框架
     * 计算0~10000000000L的和
     * 用forkjoin分组计算最后再合并
     *
     * forkjoin相比较传统的线程池的方式，好处在于采取工作窃取的方式，即某个线程的任务执行完了，会去其他线程窃取任务，而不会导致有cpu浪费空闲等待。
     */
    @Test
    public void test1(){
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0L, 10000000000L);

        long sum = pool.invoke(task);
        System.out.println(sum);

        long end = System.currentTimeMillis();

        System.out.println("耗费的时间为: " + (end - start)); //112-1953-1988-2654-2647-20663-113808
    }

    /**
     * 传统的循环方式，数量大时效率比测试1慢
     */
    @Test
    public void test2(){
        long start = System.currentTimeMillis();

        long sum = 0L;

        for (long i = 0L; i <= 10000000000L; i++) {
            sum += i;
        }

        System.out.println(sum);

        long end = System.currentTimeMillis();

        System.out.println("耗费的时间为: " + (end - start)); //34-3174-3132-4227-4223-31583
    }

    /**
     * 计算0~10000000000L的和
     * java8并行流的方式
     */
    @Test
    public void test3(){
        long start = System.currentTimeMillis();

        Long sum = LongStream.rangeClosed(0L, 10000000000L)
               // .parallel()
                .sum();

        System.out.println(sum);

        long end = System.currentTimeMillis();

        System.out.println("耗费的时间为: " + (end - start));
    }

}
