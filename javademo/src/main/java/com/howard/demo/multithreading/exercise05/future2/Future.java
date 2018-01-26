package com.howard.demo.multithreading.exercise05.future2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * jdk提供的Future模式的实现
 * RunableFuture接口继承Runable和Future，其中run方法用于构造真实数据
 * FutureTask是RunableFuture的实现，内部有个Sync类，一些实质性的工作最终会委托给Sync完成，Sync最终会调用Callable接口返回
 */
public class Future {

    public static void main(String[] args) {
        //构造futuretask
        FutureTask<String> futureTask = new FutureTask<>(new RealData("data"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(futureTask);
        System.out.println("请求完成");
        //模拟利用等待时间取操作其它事情
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            //futureTask.get() 获取真实数据
            //如果此时真实数据未获取完成 仍然会阻塞
            System.out.println("数据："+ futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
