package com.howard.interfaces;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8内置了四大核心函数式接口
 * Created by Howard Yao on 2018/6/4.
 */
public class TestFunInterFace {

    /**
     * Consumer<T> 消费型接口 :
     * 一个参数，无返回值
     */
    @Test
    public void testConsumer() {
        consumer(1000,m -> System.out.println("消费了"+m+"元"));
    }

    public void consumer(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    /**
     * Supplier<T> 供给型接口 : T get(); 无参数，带返回值
     * 情景：随机生成num个数，并放入一个集合中返回
     */
    @Test
    public void testSupplier() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer num : numList) {
            System.out.println(num);
        }
    }
    public List<Integer> getNumList(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }

        return list;
    }

    /**
     * Function<T, R> 函数型接口： R apply(T t); 带一个参数，一个返回值
     * 情景：处理字符串
     */
    @Test
    public void testFunction() {
        String str1 = "\t\t我正在学习java8新特性      ";
        System.out.println(strHandler(str1,(x) -> x.trim()));

        String str2 = "我正在学习java8新特性";
        System.out.println(strHandler(str1,(x) -> x.substring(3)));
    }

    public String strHandler(String str, Function<String, String> fun){
        return fun.apply(str);
    }

    /**
     * Predicate<T> 断言型接口： boolean test(T t); 一个参数，返回boolean
     * 情景：将满足条件的字符串放入集合中
     */
    @Test
    public void testPredicate() {
        List<String> stringList = Arrays.asList("hello","howard","java8","jdk","function");
        List<String> list = filterStr(stringList, s -> s.length() > 4);
        for (String str: list) {
            System.out.println(str);
        }
    }

    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();
        for (String str : list) {
            if(pre.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }
}
