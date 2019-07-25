package com.corgi.test.design;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title: StructuredFilterPattern
 * @description: 过滤器模式
 * 这种模式允许开发人员使用不同的标准来过滤一组对象，通过逻辑运算以解耦的方式把它们连接起来。
 * 这种类型的设计模式属于结构型模式，它结合多个标准来获得单一标准
 * @author: dengmiao
 * @create: 2019-07-25 09:46
 **/
public class StructuredFilterPattern {

    @Data
    @Accessors(chain = true)
    static
    class Person {

        private String name;
        private String gender;
        private String maritalStatus;
    }

    interface Criteria {

        List<Person> meetCriteria(List<Person> persons);
    }

    static class CriteriaMale implements Criteria {

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> malePersons = persons.stream()
                    .filter(person -> person.getGender().equalsIgnoreCase("MALE"))
                    .collect(Collectors.toList());
            return malePersons;
        }
    }

    static class CriteriaFemale implements Criteria {

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> femalePersons = persons.stream()
                    .filter(person -> person.getGender().equalsIgnoreCase("FEMALE"))
                    .collect(Collectors.toList());
            return femalePersons;
        }
    }

    static class CriteriaSingle implements Criteria {

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> singlePersons = persons.stream()
                    .filter(person -> person.getGender().equalsIgnoreCase("SINGLE"))
                    .collect(Collectors.toList());
            return singlePersons;
        }
    }

    static class AndCriteria implements Criteria {

        private Criteria criteria;
        private Criteria otherCriteria;

        public AndCriteria(Criteria criteria, Criteria otherCriteria) {
            this.criteria = criteria;
            this.otherCriteria = otherCriteria;
        }

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
            return otherCriteria.meetCriteria(firstCriteriaPersons);
        }
    }

    static class OrCriteria implements Criteria {

        private Criteria criteria;
        private Criteria otherCriteria;

        public OrCriteria(Criteria criteria, Criteria otherCriteria) {
            this.criteria = criteria;
            this.otherCriteria = otherCriteria;
        }

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> firstCriteriaItems = criteria.meetCriteria(persons);
            List<Person> otherCriteriaItems = otherCriteria.meetCriteria(persons);

            for (Person person : otherCriteriaItems) {
                if(!firstCriteriaItems.contains(person)){
                    firstCriteriaItems.add(person);
                }
            }
            return firstCriteriaItems;
        }
    }

    static class CriteriaPatternDemo {
        public static void main(String[] args) {
            List<Person> persons = new ArrayList<>();

            persons.add(new Person().setName("Robert").setGender("Male").setMaritalStatus("Single"));
            persons.add(new Person().setName("John").setGender("Male").setMaritalStatus("Married"));
            persons.add(new Person().setName("Laura").setGender("Female").setMaritalStatus("Married"));
            persons.add(new Person().setName("Diana").setGender("Female").setMaritalStatus("Single"));
            persons.add(new Person().setName("Mike").setGender("Male").setMaritalStatus("Single"));
            persons.add(new Person().setName("Bobby").setGender("Male").setMaritalStatus("Single"));

            Criteria male = new CriteriaMale();
            Criteria female = new CriteriaFemale();
            Criteria single = new CriteriaSingle();
            Criteria singleMale = new AndCriteria(single, male);
            Criteria singleOrFemale = new OrCriteria(single, female);

            System.out.println("Males: ");
            printPersons(male.meetCriteria(persons));

            System.out.println("\nFemales: ");
            printPersons(female.meetCriteria(persons));

            System.out.println("\nSingle Males: ");
            printPersons(singleMale.meetCriteria(persons));

            System.out.println("\nSingle Or Females: ");
            printPersons(singleOrFemale.meetCriteria(persons));
        }

        public static void printPersons(List<Person> persons){
            for (Person person : persons) {
                System.out.println(person);
            }
        }
    }
}
