package com.corgi.test.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-20 15:23
 **/
public class ExchangerTest {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        ExecutorService pool = Executors.newCachedThreadPool();
        //绑架者a
        pool.execute(new Runnable() {

            @Override
            public void run() {
                String data = "人质b";
                String respData;
                try {
                    respData = exchanger.exchange(data);
                    System.out.println("绑架者用\t" + data + "\t交换回" + respData);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //b的家属c
        pool.execute(new Runnable() {

            @Override
            public void run() {
                String data = "1000万";
                String respData;
                try {
                    respData = exchanger.exchange(data);
                    System.out.println("家属c用\t" + data + "\t交换回" + respData);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        pool.shutdown();
    }
}
