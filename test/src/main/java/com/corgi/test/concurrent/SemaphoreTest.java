package com.corgi.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-20 15:20
 **/
public class SemaphoreTest {

    class MyTask implements Runnable {

        /**
         * 信号量
         */
        private Semaphore semaphore;

        private int user;

        public MyTask(Semaphore semaphore, int user) {
            this.semaphore = semaphore;
            this.user = user;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();//获取信号量许可，如果获取不到信号量许可，只能等待（阻塞）
                //如果获取到信号量许可，往下执行
                System.out.println("用户" + user + "进入窗口，准备买票");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("用户" + user + "买票完成，准备离开");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("用户" + user + "离开窗口");
                //释放信号量许可
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void execute() {
        final Semaphore semaphore = new Semaphore(2);
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            pool.execute(new MyTask(semaphore, i));
        }
        pool.shutdown();
    }

    public static void main(String[] args) {
        SemaphoreTest spt = new SemaphoreTest();
        spt.execute();
    }
}
