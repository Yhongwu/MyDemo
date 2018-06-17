package com.howard.demo.multithreading.exercise06.parallelsearch;


import org.omg.CORBA.ARG_OUT;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并行搜索
 * 对于有序数组，可以使用二分查找法
 * 那么对于无序数组，则只能遍历查找
 * 单线程下则只能遍历
 * 多线程下可以使用并行搜索，即将数组分成多个，对每一个数组进行搜索，只要其中一个找到了，就立即返回
 */
public class ParallelSearch {
    //待查找的数组
    static int[] arr;
    //线程池
    static ExecutorService pool = Executors.newCachedThreadPool();
    //线程数量
    static final int Thread_Num = 2;
    //使用CAS原子操作
    static AtomicInteger result = new AtomicInteger(-1);

    /**
     * 对每一个线程顺序搜索
     * @param searchValue
     * @param beginPos
     * @param endPos
     * @return
     */
    public static int search(int searchValue,int beginPos,int endPos) {
        int i = 0;
        for (i = beginPos; i < endPos; i ++) {
            //result不为0，说明其它线程找到了，直接返回即可
            if (result.get() >= 0) {
                return result.get();
            }
            //找到待查找的数，将其放入result
            if (arr[i] == searchValue) {
                //放入使用compareAndSet，如果不成功，说明在放入前已经有其它线程找到并放入，忽视失败结果，直接返回
                if(!result.compareAndSet(-1,i)) {
                    return result.get();
                }
                return i;
            }

        }
        return -1;
    }

    public static class  SearchTask implements Callable<Integer> {
        int begin;
        int end;
        int searchValue;
        public  SearchTask(int searchValue,int begin,int end) {
            this.begin = begin;
            this.searchValue = searchValue;
            this.end = end;
        }

        @Override
        public Integer call() throws Exception {
            int re = search(searchValue,begin,end);
            return re;
        }
    }

    public static int pSearch(int searchValue) throws ExecutionException, InterruptedException {
        int subArrSize = arr.length / Thread_Num;
        List<Future<Integer>> re = new ArrayList<>();
        //将arr数组分为若干段
        for (int i = 0; i < arr.length; i += subArrSize) {
            int end = i + subArrSize;
            if (end >= arr.length) {
                end = arr.length;
            }
            re.add(pool.submit(new SearchTask(searchValue,i,end)));

        }
        for (Future<Integer> fu : re) {
            if (fu.get() >= 0) {
                return fu.get();
            }
        }
        return -1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        arr = new int[]{3,4,5,10,100,23};
        int i = pSearch(23);
        System.out.println(i);

    }
}

