package com.corgi.core.modules.sys.controller.common;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-25 22:02
 **/
public class TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Callable<Double> doubleCallable = new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return getInteger();
            }
        };
        Callable<String> stringCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getString();
            }
        };

        FutureTask<Double> doubleFutureTask = new FutureTask<>(doubleCallable);
        FutureTask<String> stringFutureTask = new FutureTask<>(stringCallable);

        new Thread(doubleFutureTask).start();
        new Thread(stringFutureTask).start();

        System.out.printf("%.2f %s\n", doubleFutureTask.get(), stringFutureTask.get());
        System.out.println("总耗时: " + (System.currentTimeMillis() - start));
    }

    public static double getInteger() throws InterruptedException {
        Thread.sleep(1000);
        return Math.random();
    }

    public static String getString() throws InterruptedException {
        Thread.sleep(3000);
        return String.valueOf(Math.random());
    }
}
