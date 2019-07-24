package com.corgi.test.design;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.corgi.test.design.SingletonPattern.SingletonInner;
import static com.corgi.test.design.SingletonPattern.SingletonEnum;

/**
 * @program: demo
 * @description: 较安全的单例模式写法
 * @author: dengmiao
 * @create: 2019-04-20 15:16
 **/
public class SingletonPattern {

    /**
     * 双锁机制+volatile
     * 懒加载
     * 无法防止反射打破现有结构
     */
    static class SingletonLock {

        /**
         * volatile修饰阻止变量访问前后的指令重排 保证了指令的执行顺序
         */
        private volatile static SingletonLock instance = null;

        private SingletonLock() {}

        public static SingletonLock getInstance() {
            //双重检测机制
            if (instance == null) {
                //同步锁
                synchronized (SingletonLock.class){
                    //双重检测机制
                    if (instance == null) {
                        instance = new SingletonLock();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * 内部类版的单例
     * 懒加载
     * 无法防止反射打破现有结构
     */
    static class SingletonInner {
        private static SingletonInner instance;

        private SingletonInner() {}

        /**
         * jvm类加载是一个线程互斥的过程
         */
        private static class Factory {
            private static SingletonInner createInstance() {
                if(instance == null) {
                    instance = new SingletonInner();
                }
                return instance;
            }
        }

        public static SingletonInner getInstance() {
            return Factory.createInstance();
        }

        public static void main(String[] args) {
            // main/psvm
            int time = 10;
            // fori
            for (int i = 0; i < time; i++) {
                // sout
                System.out.println(SingletonInner.getInstance());
            }
            // 以上, 静态内部类实现了较安全的单例
        }
    }

    /**
     * 枚举类实现单例
     * jvm会阻止通过反射获取私有构造
     * 而且线程安全
     * 非懒加载 在枚举类加载就初始化了
     */
    enum SingletonEnum {
        //使用枚举实现的单例模式，不但可以防止利用反射强行构建单例对象，而且可以在枚举类对象被反序列化的时候，保证反序列的返回结果是同一对象
        INSTANCE;
    }
}

class SingletonInnerTest {

    public static void main(String[] args) {
        // 利用反射打破构造访问禁止
        try {
            //获得构造器
            Constructor con = SingletonInner.class.getDeclaredConstructor();
            //设置为可访问
            con.setAccessible(true);
            //构造两个不同的对象
            SingletonInner singleton1 = (SingletonInner)con.newInstance();
            SingletonInner singleton2 = (SingletonInner)con.newInstance();
            //验证是否是不同对象
            System.out.println(singleton1.equals(singleton2));
        } catch (NoSuchMethodException | InstantiationException |
                IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

class SingletonEnumTest {

    public static void main(String[] args) {
        try {
            //获得构造器
            Constructor con = SingletonEnum.class.getDeclaredConstructor();
            //设置为可访问
            con.setAccessible(true);
            //构造两个不同的对象
            SingletonEnum singleton1 = (SingletonEnum)con.newInstance();
            SingletonEnum singleton2 = (SingletonEnum)con.newInstance();
            //验证是否是不同对象
            System.out.println(singleton1.equals(singleton2));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
