package com.corgi.test.design;

import java.util.ArrayList;
import java.util.List;

/**
 * @title: BehaviorObserverPattern
 * @description: 观察者模式
 * @author: dengmiao
 * @create: 2019-07-24 17:03
 **/
public class BehaviorObserverPattern {

    /**
     * 抽象被观察者接口
     */
    interface Observerable {

        /**
         * 添加
         * @param o
         */
        void registerObserver(Observer o);

        /**
         * 删除
         * @param o
         */
        void removeObserver(Observer o);

        /**
         * 通知
         */
        void notifyObserver();
    }

    /**
     * 抽象观察者
     */
    interface Observer {

        /**
         * 当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调
         * @param message
         */
        void update(String message);
    }

    static class WechatServer implements Observerable {

        /**
         * 注意到这个List集合的泛型参数为Observer接口，设计原则：面向接口编程而不是面向实现编程
         */
        private List<Observer> container;
        private String message;

        public WechatServer() {
            container = new ArrayList<>();
        }

        @Override
        public void registerObserver(Observer o) {
            container.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            if(!container.isEmpty()) {
                container.remove(o);
            }
        }

        @Override
        public void notifyObserver() {
            container.stream().forEach(item -> item.update(message));
        }

        public void push(String s) {
            this.message = s;
            System.out.println("微信服务更新消息： " + s);
            //消息更新，通知所有观察者
            notifyObserver();
        }
    }

    static class User implements Observer {

        private String name;
        private String message;

        public User(String name) {
            this.name = name;
        }

        @Override
        public void update(String message) {
            this.message = message;
            pull();
        }

        public void pull() {
            System.out.println(name + " 收到推送消息： " + message);
        }
    }

    public static void main(String[] args) {
        WechatServer server = new WechatServer();

        Observer userZhang = new User("ZhangSan");
        Observer userLi = new User("LiSi");
        Observer userWang = new User("WangWu");

        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);
        server.push("PHP是世界上最好用的语言！");

        System.out.println("----------------------------------------------");
        server.removeObserver(userZhang);
        server.push("JAVA是世界上最好用的语言！");
    }
}
