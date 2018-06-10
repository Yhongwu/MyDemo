package com.howard.lambda;

import com.howard.util.MyInterface;
import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * Created by Howard Yao on 2018/6/4.
 */
public class LambdaTest {

    /**
     * 匿名内部类
     */
    @Test
    public void test1() {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        };
        TreeSet<String> treeSet = new TreeSet<>(comparator);

        TreeSet<String> treeSet2 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        });
    }

    /**
     * 使用了Lambda表达式后
     */
    @Test
    public  void test2() {
        Comparator<String> comparator = (x,y) -> Integer.compare(x.length(),y.length());
        TreeSet<String> treeSet = new TreeSet<>(comparator);
    }

    /**
     * Lambda表达式
     * 语法格式一：无参数，无返回值
     */
    @Test
    public void test3() {

        //不使用lambda表达式
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!!!");
            }
        };
        r1.run();

        System.out.println("-----------------------");

        Runnable r2 = () -> System.out.println("Hello World!!!");
        r2.run();
    }

    /**
     * 语法格式二：有一个参数，并且无返回值
     * 语法格式三：若只有一个参数，小括号可以省略不写
     */
    @Test
    public void test4() {
        Consumer<String> con1 = (x) -> System.out.println(x);
        con1.accept("我正在学习java8新特性");

        System.out.println("-----------------------");

        Consumer<String> con2 = x -> System.out.println(x);
        con2.accept("我正在学习java8新特性：lambda表达式，若只有一个参数，括号可省略不写");
    }

    /**
     * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
     */
    @Test
    public void test5() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("我正在学习java8新特性：lambda表达式");
            return Integer.compare(x, y);
        };
    }

    /**
     * 语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
     * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
     */
    @Test
    public void test6() {

        //Comparator<Integer> com = (Integer x, Integer y) -> Integer.compare(x, y);

        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

    }

    /**
     * Lambda 表达式需要“函数式接口”的支持
     * 对一个数num进行操作
     */
    @Test
    public void test7() {
        //对一个数进行x*x操作
        System.out.println(operation(100,x -> x * x));
        //对一个数进行x+x操作
        System.out.println(operation(100,x -> x + x));
    }

    public Integer operation(Integer num, MyInterface mf){
        return mf.getValue(num);
    }
}
