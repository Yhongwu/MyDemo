package com.howard.demo.other;

/**
 * 三目运算符
 * Created by Howard Yao on 2018/11/10.
 */
public class TernaryOperator {

    public static void main(String[] args) {
        // 10
        int i = 012;
        char a = 'a';
        System.out.println((i > 11) ? i : a);
        System.out.println((i > 11) ? 65 : a);
        System.out.println((i > 11) ? 65.0 : a);
    }

    // 1.若三目运算符中的两个表达式有一个是常量表达式，另一个是类型T的表达式，且常量表达式可以被T表示，则输出结果是T类型。
    // 2.如果都是常量表达式，用向上类型转换

    // 第一个输出97 a被提升为int类型
    // 第二个输出为a 原因为65可以用类型char表示,所以输出的a仍然为char型
    // 第三个输出为97.0 原因是97.0不能用char表示 所以a转型为double
}
