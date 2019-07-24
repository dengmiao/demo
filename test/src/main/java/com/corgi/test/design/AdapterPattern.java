package com.corgi.test.design;

/**
 * @title: AdapterPattern
 * @description: 适配器模式
 * @author: dengmiao
 * @create: 2019-07-24 16:45
 **/
public class AdapterPattern {

    /**
     * 目标接口（客户端需要使用的接口）
     */
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
