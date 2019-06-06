package com.corgi.test.java8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @title: Test
 * @description:
 * @author: dengmiao
 * @create: 2019-05-31 16:19
 **/
@Slf4j
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //supplyAsync内部使用ForkJoinPool线程池执行任务
        CompletableFuture<String> completableFuture= CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            log.info("当前线程: {} task doing...", Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result";
        });
        log.info("计算结果: {}", completableFuture.get());
    }
}
