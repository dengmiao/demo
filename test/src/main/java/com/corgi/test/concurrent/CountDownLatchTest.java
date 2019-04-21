package com.corgi.test.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-20 15:25
 **/
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch cdl = new CountDownLatch(2);
        //任务1
        new Thread() {
            public void run() {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(Thread.currentThread().getName() + "正在执行。。");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(Thread.currentThread().getName() + "执行完毕。。");
                    cdl.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        //任务2
        new Thread() {
            public void run() {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(Thread.currentThread().getName() + "正在执行。。");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(Thread.currentThread().getName() + "执行完毕。。");
                    cdl.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        //同步点
        cdl.await();
        //主线程执行
        System.out.println(Thread.currentThread().getName() + "主线程执行。。");
    }
}
