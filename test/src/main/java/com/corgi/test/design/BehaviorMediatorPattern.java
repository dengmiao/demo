package com.corgi.test.design;

/**
 * @title: BehaviorMediatorPattern
 * @description: 中介者模式
 * @author: dengmiao
 * @create: 2019-07-25 11:21
 */
public class BehaviorMediatorPattern {

  /**
   * 意图：用一个中介对象来封装一系列的对象交互，中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
   *
   * <p>主要解决：对象与对象之间存在大量的关联关系，这样势必会导致系统的结构变得很复杂，同时若一个对象发生改变，我们也需要跟踪与之相关联的对象，同时做出相应的处理。
   *
   * <p>何时使用：多个类相互耦合，形成了网状结构。
   *
   * <p>如何解决：将上述网状结构分离为星型结构。
   *
   * <p>关键代码：对象 Colleague 之间的通信封装到一个类中单独处理。
   *
   * <p>应用实例： 1、中国加入 WTO 之前是各个国家相互贸易，结构复杂，现在是各个国家通过 WTO 来互相贸易。 2、机场调度系统。 3、MVC 框架，其中C（控制器）就是 M（模型）和
   * V（视图）的中介者。
   *
   * <p>优点： 1、降低了类的复杂度，将一对多转化成了一对一。 2、各个类之间的解耦。 3、符合迪米特原则。
   *
   * <p>缺点：中介者会庞大，变得复杂难以维护。
   *
   * <p>使用场景： 1、系统中对象之间存在比较复杂的引用关系，导致它们之间的依赖关系结构混乱而且难以复用该对象。 2、想通过一个中间类来封装多个类中的行为，而又不想生成太多的子类。
   *
   * <p>注意事项：不应当在职责混乱的时候使用
   */

    /**
     * 抽象牌友类
     */
    static abstract class AbstractCardPartner {
        public int Money;

        public int getMoney() {
            return Money;
        }

        public void setMoney(int money) {
            Money = money;
        }

        public abstract void ChangeMoney(int money, AbstractMediator mediator);
    }

    /**
     * 抽象中介类
     */
    static abstract class AbstractMediator {
        protected AbstractCardPartner A;
        protected AbstractCardPartner B;

        public AbstractMediator(AbstractCardPartner a, AbstractCardPartner b)
        {
            A = a;
            B = b;
        }

        public abstract void AWin(int money);
        public abstract void BWin(int money);
    }

    /**
     * 中介类
     */
    static class Mediator extends AbstractMediator {
        public Mediator(AbstractCardPartner a, AbstractCardPartner b){
            super(a, b);
        }

        @Override
        public void AWin(int money) {
            A.Money += money;
            int tmp = B.getMoney() - money;
            B.setMoney(tmp);
        }

        @Override
        public void BWin(int money) {
            B.Money += money;
            int tmp = A.getMoney() - money;
            A.setMoney(tmp);
        }
    }

    static class PartnerA extends AbstractCardPartner {

        @Override
        public void ChangeMoney(int money, AbstractMediator mediator) {
            mediator.AWin(money);
        }
    }

    static class PartnerB extends AbstractCardPartner {

        @Override
        public void ChangeMoney(int money, AbstractMediator mediator) {
            mediator.BWin(money);
        }
    }

    public static void main(String[] args){
        AbstractCardPartner A = new PartnerA();
        AbstractCardPartner B = new PartnerB();
        A.Money = 20;
        B.Money = 20;

        AbstractMediator mediator = new Mediator(A, B);

        // A赢了
        A.ChangeMoney(5, mediator);
        // 应该是25
        System.out.println("A 现在的钱是：" + A.Money);
        // 应该是15
        System.out.println("B 现在的钱是：" + B.Money);

        // B赢了
        B.ChangeMoney(10, mediator);
        // 应该是15
        System.out.println("A 现在的钱是：" + A.Money);
        // 应该是25
        System.out.println("B 现在的钱是：" + B.Money);
    }
}
