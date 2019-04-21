package com.corgi.test.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-20 15:24
 **/
public class CyclicBarrierTest {

    public static void main(String[] args) {
        final CyclicBarrier cb = new CyclicBarrier(3);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            final int user = i;
            Runnable r = new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        //记录下到了多少人
                        System.out.println(user + " 用户已经到达，当前有" + (cb.getNumberWaiting() + 1) + "人到达");
                        //屏障起，阻塞点
                        cb.await();

                        if(user == 1) {
                            System.out.println("人员到齐，开始吃饭。。");
                        }
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(user + " 用户吃完，准备离开");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            };
            pool.execute(r);
        }
        pool.shutdown();
    }
}
