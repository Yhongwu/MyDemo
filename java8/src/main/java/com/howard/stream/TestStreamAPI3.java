package com.howard.stream;

import com.howard.util.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作
 * 映射
 * 排序
 * Created by Howard Yao on 2018/6/5.
 */
public class TestStreamAPI3 {
    List<Student> stus = Arrays.asList(
            new Student(1, "李四", 19, 100),
            new Student(2, "张三", 18, 70),
            new Student(3, "王五", 15, 56),
            new Student(4, "赵六", 22, 78),
            new Student(5, "赵六", 16, 67),
            //new Student(5, "赵六", 16, 67),
            new Student(6, "田七", 28, 80)
    );

    /**
     * map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     */
    @Test
    public void test1() {
        stus.stream().map(e -> e.getName()).forEach(System.out::println);
    }

    @Test
    public void test2() {
        Arrays.asList("a","b","c","d")
                .stream()
                .map(e -> e.toUpperCase())
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        List<String> stringList = Arrays.asList("aa", "bb", "cc", "dd");

        Stream<Stream<Character>> stream = stringList.stream()
                .map(TestStreamAPI3::filterCharacter);

        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });
    }

    /**
     * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test4() {
        List<String> stringList = Arrays.asList("aa", "bb", "cc", "dd");

        Stream<Character> stream = stringList.stream()
                .flatMap(TestStreamAPI3::filterCharacter);

        stream.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    /**
     * sorted()——自然排序
     */
    @Test
    public void test5() {
        stus.stream()
                .map(Student::getName)
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * sorted(Comparator com)——定制排序
     * 先按年龄排序，一样的情况下再按名字排序
     */
    @Test
    public void test6() {
        stus.stream()
                .sorted((x, y) -> {
                    if(x.getAge() == y.getAge()){
                        return x.getName().compareTo(y.getName());
                    }else{
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }).forEach(System.out::println);
    }
}
