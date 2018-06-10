package com.howard.stream;

import com.howard.util.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作
 * 筛选与切片
 * Created by Howard Yao on 2018/6/5.
 */
public class TestStreamAPI2 {


    List<Student> stus = Arrays.asList(
            new Student(1, "李四", 19, 100),
            new Student(2, "张三", 18, 70),
            new Student(3, "王五", 15, 56),
            new Student(4, "赵六", 22, 78),
            new Student(5, "赵六", 16, 67),
            new Student(5, "赵六", 16, 67),
            new Student(6, "田七", 28, 80)
    );

    /**
     * filter——接收 Lambda ， 从流中排除某些元素。
     */
    @Test
    public void test1() {
        Stream<Student> stream = stus.stream()
                .filter( e -> {
                    System.out.println("中间操作");
                    return e.getAge() >= 18;
                });
        stream.forEach(System.out::println);
    }

    /**
     * limit——截断流，使其元素不超过给定数量。
     */
    @Test
    public void test2() {
        stus.stream()
                .filter( e -> e.getAge() >= 18)
                .limit(2)  //截断流
                .forEach(System.out::println);
    }

    /**
     *  skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     */
    @Test
    public void test3() {
        stus.stream()
                .filter( e -> e.getAge() >= 18)
                .skip(2)  //截断流
                .forEach(System.out::println);
    }

    /**
     * distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */
    @Test
    public void test4() {
        stus.stream()
                .filter( e -> e.getAge() >= 18)
                .distinct()
                .forEach(System.out::println);
    }
}
