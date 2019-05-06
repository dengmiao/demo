package com.corgi.test;

/**
 * @program: demo
 * @description: 最安全的单例模式写法--内部类
 * @author: dengmiao
 * @create: 2019-04-20 15:16
 **/
public class Singleton {

    private static Singleton instance;

    private Singleton() {}

    /**
     * jvm类加载是一个线程互斥的过程
     */
    private static class Factory {
        private static Singleton createInstance() {
            if(instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }

    public static Singleton getInstance() {
        return Factory.createInstance();
    }

    public static void main(String[] args) {
        // main/psvm
        int time = 10;
        // fori
        for (int i = 0; i < time; i++) {
            // sout
            System.out.println(Singleton.getInstance());
        }
    }
}
