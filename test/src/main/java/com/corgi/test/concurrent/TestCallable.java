package com.corgi.test.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-25 22:02
 **/
public class TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Callable<Double> doubleCallable = () -> getDouble();
        Callable<String> stringCallable = () -> getString();

        FutureTask<Double> doubleFutureTask = new FutureTask<>(doubleCallable);
        FutureTask<String> stringFutureTask = new FutureTask<>(stringCallable);

        new Thread(doubleFutureTask).start();
        new Thread(stringFutureTask).start();

        System.out.printf("%.2f %s\n", doubleFutureTask.get(), stringFutureTask.get());
        System.out.println("并行总耗时: " + (System.currentTimeMillis() - start));

        long s0 = System.currentTimeMillis();
        countDownLatch();
        System.out.println("CountDownLatch总耗时: " + (System.currentTimeMillis() - s0));

        long s = System.currentTimeMillis();
        System.out.printf("%.2f %s\n", getDouble(), getString());
        System.out.printf("串行总耗时: %s\n", System.currentTimeMillis() - s);
    }

    public static void countDownLatch() {
        final CountDownLatch cdl = new CountDownLatch(2);

        FutureTask<Double> doubleFutureTask = new FutureTask<>(() -> {
            try {
                return getDouble();
            } finally{
                cdl.countDown();
            }
        });
        FutureTask<String> stringFutureTask = new FutureTask<>(() -> {
            try {
                return getString();
            } finally{
                cdl.countDown();
            }
        });

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("async-task-runner-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        try {
            singleThreadPool.execute(doubleFutureTask);
            singleThreadPool.execute(stringFutureTask);
            cdl.await();
            System.out.printf("%.2f %s\n", doubleFutureTask.get(), stringFutureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            singleThreadPool.shutdown();
        }
    }

    public static double getDouble() throws InterruptedException {
        Thread.sleep(1000);
        return Math.random();
    }

    public static String getString() throws InterruptedException {
        Thread.sleep(3000);
        return String.valueOf(Math.random());
    }
}
