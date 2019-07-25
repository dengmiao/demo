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
public class StructuredDecoratorPattern {

  /**
   * 意图：动态地给一个对象添加一些额外的职责。就增加功能来说，装饰器模式相比生成子类更为灵活。
   *
   * <p>主要解决：一般的，我们为了扩展一个类经常使用继承方式实现，由于继承为类引入静态特征，并且随着扩展功能的增多，子类会很膨胀。
   *
   * <p>何时使用：在不想增加很多子类的情况下扩展类。
   *
   * <p>如何解决：将具体功能职责划分，同时继承装饰者模式。
   *
   * <p>关键代码： 1、Component 类充当抽象角色，不应该具体实现。 2、修饰类引用和继承 Component 类，具体扩展类重写父类方法。
   *
   * <p>应用实例： 1、孙悟空有 72 变，当他变成"庙宇"后，他的根本还是一只猴子，但是他又有了庙宇的功能。
   * 2、不论一幅画有没有画框都可以挂在墙上，但是通常都是有画框的，并且实际上是画框被挂在墙上。在挂在墙上之前，画可以被蒙上玻璃，装到框子里；这时画、玻璃和画框形成了一个物体。
   *
   * <p>优点：装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展一个实现类的功能。
   *
   * <p>缺点：多层装饰比较复杂。
   *
   * <p>使用场景： 1、扩展一个类的功能。 2、动态增加功能，动态撤销。
   *
   * <p>注意事项：可代替继承。
   */

  /** 抽象组件，定义了一组抽象的接口，指定了被装饰的组件都有哪些功能 */
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
