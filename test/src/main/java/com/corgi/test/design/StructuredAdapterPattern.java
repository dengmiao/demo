package com.corgi.test.design;

/**
 * @title: StructuredAdapterPattern
 * @description: 适配器模式
 * @author: dengmiao
 * @create: 2019-07-24 16:45
 **/
public class StructuredAdapterPattern {

  /**
   * 意图：将一个类的接口转换成客户希望的另外一个接口。适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
   *
   * <p>主要解决：主要解决在软件系统中，常常要将一些"现存的对象"放到新的环境中，而新环境要求的接口是现对象不能满足的。
   *
   * <p>何时使用： 1、系统需要使用现有的类，而此类的接口不符合系统的需要。
   * 2、想要建立一个可以重复使用的类，用于与一些彼此之间没有太大关联的一些类，包括一些可能在将来引进的类一起工作，这些源类不一定有一致的接口。
   * 3、通过接口转换，将一个类插入另一个类系中。（比如老虎和飞禽，现在多了一个飞虎，在不增加实体的需求下，增加一个适配器，在里面包容一个虎对象，实现飞的接口。）
   *
   * <p>如何解决：继承或依赖（推荐）。
   *
   * <p>关键代码：适配器继承或依赖已有的对象，实现想要的目标接口。
   *
   * <p>应用实例： 1、美国电器 110V，中国 220V，就要有一个适配器将 110V 转化为 220V。 2、JAVA JDK 1.1 提供了 Enumeration 接口，而在 1.2
   * 中提供了 Iterator 接口，想要使用 1.2 的 JDK，则要将以前系统的 Enumeration 接口转化为 Iterator 接口，这时就需要适配器模式。 3、在 LINUX
   * 上运行 WINDOWS 程序。 4、JAVA 中的 jdbc。
   *
   * <p>优点： 1、可以让任何两个没有关联的类一起运行。 2、提高了类的复用。 3、增加了类的透明度。 4、灵活性好。
   *
   * <p>缺点： 1、过多地使用适配器，会让系统非常零乱，不易整体进行把握。比如，明明看到调用的是 A 接口，其实内部被适配成了 B
   * 接口的实现，一个系统如果太多出现这种情况，无异于一场灾难。因此如果不是很有必要，可以不使用适配器，而是直接对系统进行重构。 2.由于 JAVA
   * 至多继承一个类，所以至多只能适配一个适配者类，而且目标类必须是抽象类。
   *
   * <p>使用场景：有动机地修改一个正常运行的系统的接口，这时应该考虑使用适配器模式。
   *
   * <p>注意事项：适配器不是在详细设计时添加的，而是解决正在服役的项目的问题。
   */

  /** 目标接口（客户端需要使用的接口） */
  interface TargetVga {

        /**
         * 定义一个VGA接口
         * @param
         */
        void vgaInterface();
    }

    /**
     * 源接口（需要被适配的接口）
     * 定义一个手机Phone，它有一个Typec接口
     */
    static class AdaptedPhone {

        void typecPhone() {
            System.out.println("信息从Typec口的手机输出。");
        }
    }

    /**
     * 适配器，将源接口适配成目标接口
     * 实现一个Type-c转VGA适配器
     * 1类适配器
     */
    static class AdapterTypecToVgaOne extends AdaptedPhone implements TargetVga {

        @Override
        public void vgaInterface() {
            typecPhone();
            // do something
            System.out.println("接收到Type-c口信息，信息转换成VGA接口中...");
            System.out.println("信息已转换成VGA接口，显示屏可以对接。");
        }
    }

    /**
     * 2对象适配器
     */
    static class AdapterTypecToVgaTwo implements TargetVga {

        private AdaptedPhone phone;

        public AdapterTypecToVgaTwo(AdaptedPhone phone) {
            this.phone = phone;
        }

        @Override
        public void vgaInterface() {
            if(phone != null) {
                phone.typecPhone();
                // do something
                System.out.println("接收到Type-c口信息，信息转换成VGA接口中...");
                System.out.println("信息已转换成VGA接口，显示屏可以对接。");
            }
        }
    }

    static abstract class AbstractAdapterTypecToVga implements TargetVga {

        @Override
        public void vgaInterface() {

        }
    }

    /**
     * 抽象类适配器
     */
    static class AbstractAdapterTypecToVgaThree extends AbstractAdapterTypecToVga {

        @Override
        public void vgaInterface() {
            System.out.println("接收到Type-c口信息，信息转换成VGA接口中...");
            System.out.println("信息已转换成VGA接口，显示屏可以对接。");
        }
    }

    public static void main(String[] args) {
        //第一种适配器用法
        System.out.println("-------------第一种适配器------------");
        TargetVga vga = new AdapterTypecToVgaOne();
        vga.vgaInterface();//适配器将typec转换成vga
        System.out.println("显示屏对接适配器，手机成功投影到显示屏!");

        //第二种适配器用法
        System.out.println("-------------第二种适配器------------");
        AdapterTypecToVgaTwo typec2Vga1 = new AdapterTypecToVgaTwo(new AdaptedPhone());
        typec2Vga1.vgaInterface();//适配器将typec转换成vga
        System.out.println("显示屏对接适配器，手机成功投影到显示屏!");

        //第三种适配器用法
        System.out.println("-------------第三种适配器------------");
        AbstractAdapterTypecToVgaThree vgaAdapter = new AbstractAdapterTypecToVgaThree();
        vgaAdapter.vgaInterface();//适配器将typec转换成vga
        System.out.println("显示屏对接适配器，手机成功投影到显示屏!");
    }
}
