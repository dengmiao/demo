package com.corgi.test.design;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.corgi.test.design.CreationSingletonPattern.SingletonInner;
import static com.corgi.test.design.CreationSingletonPattern.SingletonEnum;

/**
 * @program: demo
 * @description: 较安全的单例模式写法
 * @author: dengmiao
 * @create: 2019-04-20 15:16
 **/
public class CreationSingletonPattern {

    /**
     * 一般情况下，不建议使用懒汉方式，建议使用饿汉方式。
     * 只有在要明确实现 lazy loading 效果时，才会使用登记方式。
     * 如果涉及到反序列化创建对象时，可以尝试使用枚举方式。
     * 如果有其他特殊的需求，可以考虑使用双检锁方式
     */

    /**
     * 懒汉式
     * 懒加载 线程相对安全
     * 能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步
     */
    static class SingletonLazy {
        private static SingletonLazy instance;
        private SingletonLazy (){}
        public static synchronized SingletonLazy getInstance() {
            if (instance == null) {
                instance = new SingletonLazy();
            }
            return instance;
        }
    }

    /**
     * 饿汉式
     * 基于 classloader 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化
     */
    static class SingletonAdvance {
        private static SingletonAdvance instance = new SingletonAdvance();
        private SingletonAdvance (){}
        public static SingletonAdvance getInstance() {
            return instance;
        }
    }

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
     * 登记式/静态内部类 内部类版的单例
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
