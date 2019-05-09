package com.corgi.test.java8;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @title: ParallelStream
 * @description: 并行流测试
 * @author: dengmiao
 * @create: 2019-05-09 14:29
 **/
public class ParallelStream {

    public static void main(String[] args) {
        // 并行流默认使用的线程池 ForkJoinPool.commonPool
        // 默认的线程数是当前机器的cpu个数
        // 修改默认线程数
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        IntStream.range(1, 10000).parallel().peek(ParallelStream::debug).count();
    }

    public static void debug(final int i) {
        System.out.println(Thread.currentThread().getName() + "-debug-" + i);
        try {
            TimeUnit.MILLISECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
