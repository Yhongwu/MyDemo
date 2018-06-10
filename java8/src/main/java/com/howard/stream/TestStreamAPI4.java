package com.howard.stream;

import com.howard.util.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 * 匹配与查找
 * 归约
 * 收集
 * Created by Howard Yao on 2018/6/5.
 */
public class TestStreamAPI4 {
    List<Student> stus = Arrays.asList(
            new Student(1, "李四", 19, 100, Student.Status.GOOD),
            new Student(2, "张三", 18, 70, Student.Status.MEDIUM),
            new Student(3, "王五", 15, 56, Student.Status.NOPASS),
            new Student(4, "赵六", 22, 78,Student.Status.MEDIUM),
            new Student(5, "赵六", 16, 67,Student.Status.MEDIUM),
            //new Student(5, "赵六", 16, 67),
            new Student(6, "田七", 28, 80,Student.Status.GOOD)
    );
    /*
        allMatch——检查是否匹配所有元素
        anyMatch——检查是否至少匹配一个元素
        noneMatch——检查是否没有匹配的元素
        findFirst——返回第一个元素
        findAny——返回当前流中的任意元素
        count——返回流中元素的总个数
        max——返回流中最大值
        min——返回流中最小值
    */

    /**
     * allMatch——检查是否匹配所有元素
     * 是否所有学生都是优秀的
     */
    @Test
    public void test1() {
        boolean flag = stus.stream()
                .allMatch(e -> e.getStatus().equals(Student.Status.GOOD));
        System.out.println(flag);
    }

    /**
     * anyMatch——检查是否至少匹配一个元素
     * 是否至少有一个学生是优秀的
     */
    @Test
    public void test2() {
        boolean flag = stus.stream()
                .anyMatch(e -> e.getStatus().equals(Student.Status.GOOD));
        System.out.println(flag);
    }

    /**
     * noneMatch——检查是否没有匹配的元素
     * 是否没有学生是优秀的
     */
    @Test
    public void test3() {
        boolean flag = stus.stream()
                .noneMatch(e -> e.getStatus().equals(Student.Status.GOOD));
        System.out.println(flag);
    }
    /**
     * findFirst——返回第一个元素
     */
    @Test
    public void test4() {
        Optional<Student> optional = stus.stream()
                .sorted((x, y) -> -Double.compare(x.getScore(), y.getScore()))
                .findFirst();

        System.out.println(optional.get());
    }
    /**
     * findAny——返回当前流中的任意元素
     */
    @Test
    public void test5() {
        Optional<Student> optional = stus.parallelStream()
                .filter( e -> e.getStatus().equals(Student.Status.MEDIUM))
                .findAny();

        System.out.println(optional.get());
    }

    /**
     * count——返回流中元素的总个数
     * 成绩中等的学生人数
     */
    @Test
    public void test6() {
        long count = stus.parallelStream()
                .filter(e -> e.getStatus().equals(Student.Status.MEDIUM))
                .count();

        System.out.println(count);
    }
    /**
     * max——返回流中最大值
     * min——返回流中最小值
     * 学生成绩中等的学生中，成绩最高的分数和最低的分数
     */
    @Test
    public void test7() {
        Optional<Double> max = stus.parallelStream()
                .filter(e -> e.getStatus().equals(Student.Status.MEDIUM))
                .map(e -> e.getScore())
                .max(Double::compareTo);

        System.out.println(max.get());

        Optional<Double> min = stus.parallelStream()
                .filter(e -> e.getStatus().equals(Student.Status.MEDIUM))
                .map(e -> e.getScore())
                .min(Double::compareTo);
        System.out.println(min.get());
    }

    /**
     * 归约
     * 从0开始，将数组元素进行相加
     */
    @Test
    public void test8() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer reduce = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
    }

    /**
     * 学生成绩总分
     */
    @Test
    public void test9() {
        Optional<Double> reduce = stus.stream()
                .map(e -> e.getScore())
                .reduce(Double::sum);

        System.out.println(reduce.get());
    }

    //需求：搜索名字中 “六” 出现的次数
    @Test
    public void test10(){
        Optional<Integer> sum = stus.stream()
                .map(Student::getName)
                .flatMap(TestStreamAPI3::filterCharacter)
                .map((ch) -> {
                    if(ch.equals('六'))
                        return 1;
                    else
                        return 0;
                }).reduce(Integer::sum);

        System.out.println(sum.get());
    }

    /**
     * 收集
     * 把学生名字收集到list
     */
    @Test
    public void test11(){
        List<String> list = stus.stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println(list);
    }

    /**
     * 收集
     * 把学生名字收集到Set
     */
    @Test
    public void test12(){
        Set<String> set = stus.stream()
                .map(Student::getName)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);

        HashSet<String> hs = stus.stream()
                .map(Student::getName)
                .collect(Collectors.toCollection(HashSet::new));

        hs.forEach(System.out::println);
    }

    //分组
    @Test
    public void test13(){
        Map<Student.Status, List<Student>> map = stus.stream()
                .collect(Collectors.groupingBy(Student::getStatus));

        System.out.println(map);
    }

    //多级分组
    @Test
    public void test14(){
        Map<Student.Status, Map<String, List<Student>>> map = stus.stream()
                .collect(Collectors.groupingBy(Student::getStatus, Collectors.groupingBy((e) -> {
                    if(e.getAge() >= 18)
                        return "成年";
                    else
                        return "未成年";
                })));

        System.out.println(map);
    }

    //分区
    @Test
    public void test15(){
        Map<Boolean, List<Student>> map = stus.stream()
                .collect(Collectors.partitioningBy((e) -> e.getScore() >= 60));

        System.out.println(map);
    }

    /**
     * 获取学生名字并自定义分隔符
     */
    @Test
    public void test16(){
        String str = stus.stream()
                .map(Student::getName)
                .collect(Collectors.joining("," , "----", "----"));

        System.out.println(str);
    }
}
