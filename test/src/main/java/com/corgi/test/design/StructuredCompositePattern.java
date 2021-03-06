package com.corgi.test.design;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @title: StructuredCompositePattern
 * @description: 组合模式
 * 又叫部分整体模式，是用于把一组相似的对象当作一个单一的对象。
 * 组合模式依据树形结构来组合对象，用来表示部分以及整体层次。这种类型的设计模式属于结构型模式，它创建了对象组的树形结构
 * @author: dengmiao
 * @create: 2019-07-25 10:02
 **/
public class StructuredCompositePattern {

  /**
   * 意图：将对象组合成树形结构以表示"部分-整体"的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性。
   *
   * <p>主要解决：它在我们树型结构的问题中，模糊了简单元素和复杂元素的概念，客户程序可以向处理简单元素一样来处理复杂元素，从而使得客户程序与复杂元素的内部结构解耦。
   *
   * <p>何时使用： 1、您想表示对象的部分-整体层次结构（树形结构）。 2、您希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象。
   *
   * <p>如何解决：树枝和叶子实现统一接口，树枝内部组合该接口。
   *
   * <p>关键代码：树枝内部组合该接口，并且含有内部属性 List，里面放 Component。
   *
   * <p>应用实例： 1、算术表达式包括操作数、操作符和另一个操作数，其中，另一个操作符也可以是操作数、操作符和另一个操作数。 2、在 JAVA AWT 和 SWING 中，对于 Button
   * 和 Checkbox 是树叶，Container 是树枝。
   *
   * <p>优点： 1、高层模块调用简单。 2、节点自由增加。
   *
   * <p>缺点：在使用组合模式时，其叶子和树枝的声明都是实现类，而不是接口，违反了依赖倒置原则。
   *
   * <p>使用场景：部分、整体场景，如树形菜单，文件、文件夹的管理。
   *
   * <p>注意事项：定义时为具体类。
   */
  @Data
  @Accessors(chain = true)
  static class Employee {
        private String name;
        private String dept;
        private int salary;
        private List<Employee> subordinates = new ArrayList<>();

        public void add(Employee e) {
            subordinates.add(e);
        }

        public void remove(Employee e) {
            subordinates.remove(e);
        }
    }

    static class CompositePatternDemo {
        public static void main(String[] args) {
            Employee CEO = new Employee().setName("John").setDept("CEO").setSalary(30000);

            Employee headSales = new Employee().setName("Robert").setDept("Head Sales").setSalary(20000);

            Employee headMarketing = new Employee().setName("Michel").setDept("Head Marketing").setSalary(20000);

            Employee clerk1 = new Employee().setName("Laura").setDept("Marketing").setSalary(10000);
            Employee clerk2 = new Employee().setName("Bob").setDept("Marketing").setSalary(10000);

            Employee salesExecutive1 = new Employee().setName("Richard").setDept("Sales").setSalary(10000);
            Employee salesExecutive2 = new Employee().setName("Rob").setDept("Sales").setSalary(10000);

            CEO.add(headSales);
            CEO.add(headMarketing);

            headSales.add(salesExecutive1);
            headSales.add(salesExecutive2);

            headMarketing.add(clerk1);
            headMarketing.add(clerk2);

            //打印该组织的所有员工
            System.out.println(CEO);
            for (Employee headEmployee : CEO.getSubordinates()) {
                System.out.println(headEmployee);
                for (Employee employee : headEmployee.getSubordinates()) {
                    System.out.println(employee);
                }
            }
        }
    }
}
