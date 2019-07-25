package com.corgi.test.design;

import lombok.Data;

import java.util.Hashtable;

/**
 * @title: CreationPrototypePattern
 * @description: 原型模式
 * 用于创建重复的对象，同时又能保证性能
 * @author: dengmiao
 * @create: 2019-07-25 09:27
 **/
public class CreationPrototypePattern {

    /**
     * 实现了一个原型接口，该接口用于创建当前对象的克隆。当直接创建对象的代价比较大时，则采用这种模式。
     * 例如，一个对象需要在一个高代价的数据库操作之后被创建。
     * 我们可以缓存该对象，在下一个请求时返回它的克隆，在需要的时候更新数据库，以此来减少数据库调用
     */

    @Data
    abstract static class Shape implements Cloneable {

        private String id;

        protected String type;

        abstract void draw();

        @Override
        public Object clone() {
            Object clone = null;
            try {
                clone = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return clone;
        }
    }

    static class Rectangle extends Shape {

        public Rectangle(){
            type = "Rectangle";
        }

        @Override
        public void draw() {
            System.out.println("Inside Rectangle::draw() method.");
        }
    }

    static class Square extends Shape {

        public Square(){
            type = "Square";
        }

        @Override
        public void draw() {
            System.out.println("Inside Square::draw() method.");
        }
    }

    static class Circle extends Shape {

        public Circle(){
            type = "Circle";
        }

        @Override
        public void draw() {
            System.out.println("Inside Circle::draw() method.");
        }
    }

    static class ShapeCache {

        private static Hashtable<String, Shape> shapeMap
                = new Hashtable<String, Shape>();

        public static Shape getShape(String shapeId) {
            Shape cachedShape = shapeMap.get(shapeId);
            return (Shape) cachedShape.clone();
        }

        // 对每种形状都运行数据库查询，并创建该形状
        // shapeMap.put(shapeKey, shape);
        // 例如，我们要添加三种形状
        public static void loadCache() {
            Circle circle = new Circle();
            circle.setId("1");
            shapeMap.put(circle.getId(),circle);

            Square square = new Square();
            square.setId("2");
            shapeMap.put(square.getId(),square);

            Rectangle rectangle = new Rectangle();
            rectangle.setId("3");
            shapeMap.put(rectangle.getId(),rectangle);
        }
    }

    static class PrototypePatternDemo {

        public static void main(String[] args) {
            ShapeCache.loadCache();

            Shape clonedShape = (Shape) ShapeCache.getShape("1");
            System.out.println("Shape : " + clonedShape.getType());

            Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
            System.out.println("Shape : " + clonedShape2.getType());

            Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
            System.out.println("Shape : " + clonedShape3.getType());
        }
    }
}
