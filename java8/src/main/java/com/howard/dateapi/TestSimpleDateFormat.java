package com.howard.dateapi;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * jdk1.8之前的日期操作是非线程安全的
 * Created by Howard Yao on 2018/6/5.
 */
public class TestSimpleDateFormat {
    /**
     * 模拟多线程操作日期api 运行后会发生异常
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        //模拟多线程操作日期api 运行后会发生异常
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> task = new Callable<Date>() {

            @Override
            public Date call() throws Exception {
                return sdf.parse("20161121");
            }

        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> future : results) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }

    /**
     * 使用ThrealLocal解决线程安全问题
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        //使用ThrealLocal
        //解决多线程安全问题
		Callable<Date> task = new Callable<Date>() {

			@Override
			public Date call() throws Exception {
				return DateFormatThreadLocal.convert("20161121");
			}

		};

		ExecutorService pool = Executors.newFixedThreadPool(10);

		List<Future<Date>> results = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			results.add(pool.submit(task));
		}

		for (Future<Date> future : results) {
			System.out.println(future.get());
		}

		pool.shutdown();
    }

    /**
     * 使用java8的LocalDate和DateTimeFormatter
     * 不存在线程安全问题
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = new Callable<LocalDate>() {

            @Override
            public LocalDate call() throws Exception {
                LocalDate ld = LocalDate.parse("20161121", dtf);
                return ld;
            }

        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> future : results) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {




    }
}
