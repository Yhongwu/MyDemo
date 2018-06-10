package com.howard.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 创建Stream的方式
 * Created by Howard Yao on 2018/6/5.
 */
public class TestStreamAPI {

    /**
     * 创建Stream的方式
     */
    @Test
    public void test1() {
        //1、通过collection获取流
        List<String> list = new ArrayList<>();
        //获取串行流
        Stream<String> stream1 = list.stream();
        //获取并行流
        Stream<String> parallelStream1 = list.parallelStream();

        //2、通过Arrays中的stream()获取数组流
        Integer[] numArr = new Integer[5];
        Stream<Integer> stream2 = Arrays.stream(numArr);

        //3、通过Stream类的静态方法of()获取流
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);

        //4、无限流
        //迭代
        //Stream<Integer> iterate = Stream.iterate(0, x -> x + 2).limit(10);
        //iterate.forEach(System.out::println);

        //生成
        Stream<Double> limit = Stream.generate(Math::random).limit(10);
        limit.forEach(System.out::println);
    }
}
