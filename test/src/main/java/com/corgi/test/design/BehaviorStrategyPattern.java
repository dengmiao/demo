package com.corgi.test.design;

/**
 * @title: BehaviorStrategyPattern
 * @description: 策略模式
 * @author: dengmiao
 * @create: 2019-07-24 17:41
 **/
public class BehaviorStrategyPattern {
    // https://github.com/seasidesky/technology-talk/blob/master/basic-knowledge/%E5%B8%B8%E7%94%A8%E7%9A%84%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F.md
    // https://www.cnblogs.com/luohanguo/p/7825656.html
    // https://www.runoob.com/design-pattern/strategy-pattern.html

    interface Weapon {

        void gun();
    }

    static class FirstGun implements Weapon {

        @Override
        public void gun() {
            System.out.println("使用AWM狙击步枪。");
        }
    }

    static class SecondGun implements Weapon {

        @Override
        public void gun() {
            System.out.println("使用S12K霰弹枪。");
        }
    }

    static class Context {

        Weapon weapon;

        public Context(Weapon weapon) { //构造函数
            super();
            this.weapon = weapon;
        }

        public Weapon getWeapon() { //get方法
            return weapon;
        }

        public void setWeapon(Weapon weapon) { //set方法
            this.weapon = weapon;
        }

        public void gun() {
            weapon.gun();
        }
    }

    public static void main(String[] args) {
        //使用构造函数默认选择一把AWM狙击步枪（一个策略）
        Context context=new Context(new FirstGun());
        context.gun();

        //使用get、set方法切换到S12K霰弹枪（切换策略）
        System.out.println("------右前方30米发现敌人------");
        context.setWeapon(new SecondGun());
        context.gun();
    }
}
