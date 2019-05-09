package com.corgi.test.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @title: Collect
 * @description: 收集器
 * @author: dengmiao
 * @create: 2019-05-09 14:56
 **/
public class Collect {

    static List<Person> people = new ArrayList<Person>(){
        {
            add(new Person("Tom", 18, Gender.MALE, Grade.ONE));
            add(new Person("Bob", 11, Gender.MALE, Grade.THREE));
            add(new Person("Joan", 20, Gender.FEMALE, Grade.TWO));
            add(new Person("John", 22, Gender.MALE, Grade.FOUR));
            add(new Person("Mike", 9, Gender.MALE, Grade.FOUR));
        }
    };

    public static void main(String[] args) {
        Map<String, Person> map = people.stream().collect(Collectors.toMap(Person::getName, a -> a,(k1, k2)->k1));
        System.out.println(map);
        // 汇总信息 (p) -> p.age
        IntSummaryStatistics intSummaryStatistics = people.stream().collect(Collectors.summarizingInt(Person::getAge));
        System.out.println("年龄汇总: " + intSummaryStatistics);
    }
}

enum Gender {

    MALE(1), FEMALE(0);

    final int value;

    Gender(int value) {
        this.value = value;
    }
}

enum Grade {

    ONE("A"), TWO("B"), THREE("C"), FOUR("D");

    final String value;

    Grade (String value) {
        this.value = value;
    }
}

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
class Person {

    String name;

    Integer age;

    Gender gender;

    Grade grade;
}
