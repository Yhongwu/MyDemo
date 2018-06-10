package com.howard.ref;

import com.howard.util.Student;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 * Created by Howard Yao on 2018/6/4.
 */
public class TestMethodRef {

    @Test
    public void test1() {
        Consumer<String> consumre1 = str -> System.out.println(str);
        consumre1.accept("我正在学习java8新特性\n");

        PrintStream ps = System.out;
        Consumer<String> consumre2 = str -> ps.println(str);
        consumre2.accept("我正在学习java8新特性\n");

        //若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
        //可以将方法引用理解为 Lambda 表达式的另外一种表现形式
        Consumer<String> consumre3 = System.out::println;
        consumre3.accept("我正在学习java8新特性\n");

    }

    /**
     * 对象引用：：实例方法名
     */
    @Test
    public void test2() {
        Student stu = new Student(1,"tom",20,82.0);

        //lambda表达式
        Supplier<String> sup = () -> stu.getName();
        System.out.println(sup.get());

        //对象引用
        Supplier<String> sup2 = stu::getName;
        System.out.println(sup2.get());
    }

    /**
     * 类名 :: 静态方法名
     */
    @Test
    public void test3() {
        //lambda表达式
        BiFunction<Double, Double, Double> fun = (x, y) -> Math.max(x, y);
        System.out.println(fun.apply(1.5, 22.2));

        //方法引用
        BiFunction<Double, Double, Double> fun2 = Math::max;
        System.out.println(fun2.apply(1.2, 1.5));
    }

    /**
     * 类名 :: 静态方法名
     */
    @Test
    public void test4() {
        Comparator<Integer> comparator1 = (x,y) -> Integer.compare(x,y);

        Comparator<Integer> comparator2 = Integer::compare;
    }

    /**
     * 类名::实例方法
     */
    @Test
    public void test5() {
        //lambda表达式
        //equals方法的调用者是x，equals方法参数是y
        BiPredicate<String,String> bp = (x,y) -> x.equals(y);
        System.out.println(bp.test("abcd","abcd"));

        //方法引用
        BiPredicate<String,String> bp2 = String::equals;
        System.out.println(bp2.test("abcd","abcd"));
    }

}
