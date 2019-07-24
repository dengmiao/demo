package com.corgi.test.design;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @title: DecoratorPattern
 * @description: 装饰者模式
 * 在保持原有功能不变的情况下将一个类重新装饰，使其具有更强大的功能，用一句成语形容“锦上添花”
 * @author: dengmiao
 * @create: 2019-07-24 16:23
 **/
public class DecoratorPattern {

    /**
     * 抽象组件，定义了一组抽象的接口，指定了被装饰的组件都有哪些功能
     */
    interface Component {
        /**
         * method
         * @param arg
         * @return
         */
        Object decorated(Object arg);
    }

    /**
     * 抽象组件实现类，完成了基本的功能实现
     */
    static class ComponentImpl implements Component {

        @Override
        public Object decorated(Object arg) {
            System.out.println("ComponentImpl: " + arg);
            return arg;
        }
    }

    /**
     * 装饰器角色，持有Component的实例引用，有点递归的感觉
     */
    @Data
    static class Decorator implements Component {

        Component component;

        @Override
        public Object decorated(Object arg) {
            component.decorated(arg);
            System.out.println("Decorator: " + arg);
            return arg;
        }
    }

    public static void main(String[] args) {
        Component c = new ComponentImpl();
        Decorator d1 = new Decorator();
        d1.setComponent(c);
        Decorator d2 = new Decorator();
        d2.setComponent(d1);
        Decorator d3 = new Decorator();
        d3.setComponent(d2);
        Decorator d4 = new Decorator();
        d4.setComponent(d3);
        d4.decorated(new Object());
    }
}

@Data
@AllArgsConstructor
class Person {

    private String name;

    public void show() {

    }
}
