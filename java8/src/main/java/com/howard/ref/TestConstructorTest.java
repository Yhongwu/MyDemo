package com.howard.ref;

import com.howard.util.Student;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用
 * Created by Howard Yao on 2018/6/4.
 */
public class TestConstructorTest {

    /**
     * 构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致
     * 语法格式：类名 :: new
     */
    @Test
    public void test1() {
        //lambda表达式实现
        Supplier<Student> sup = () -> new Student();
        System.out.println(sup.get());

        //构造器引用
        Supplier<Student> sup2 = Student::new;
        System.out.println(sup2.get());

    }

    /**
     * 数组引用 类型[]::new
     * 本质与构造器引用类似
     */
    @Test
    public void test2() {
        //lambda表达式实现
        Function<Integer,String[]> function = (length) -> new String[length];
        String[] str = function.apply(10);
        System.out.println(str.length);

        //数组引用
        Function<Integer,String[]> function2 = String[]::new;
        String[] str2 = function2.apply(10);
        System.out.println(str2.length);
    }
}
