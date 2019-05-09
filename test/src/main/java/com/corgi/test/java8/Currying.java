package com.corgi.test.java8;

import java.util.function.Function;

/**
 * @title: Currying
 * @description: 柯里化
 * @author: dengmiao
 * @create: 2019-05-09 16:45
 **/
public class Currying {

    public static void main(String[] args) {
        Function<Integer, Function<Integer, Function<Integer, Integer>>> currying = x -> y -> z -> (x+y)*z;

        System.out.println(currying.apply(4).apply(5).apply(6));
    }
}
