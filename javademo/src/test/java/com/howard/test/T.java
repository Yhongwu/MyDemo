package com.howard.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        integers.forEach((Integer integer) -> System.out.println(integer));

    }
}
