package com.corgi.test.guava;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @title: GuavaTest
 * @description:
 * @author: dengmiao
 * @create: 2019-05-08 21:20
 **/
public class GuavaTest {

    public static void main(String[] args) {

        //获取用户集合
        List<User> list = new ArrayList<User>(){
            {
                add(new User("tom", 18));
                add(new User("bob", 11));
                add(new User("Joan", 20));
                add(new User("John", 22));
                add(new User("Mike", 9));
            }
        };

        /**
         * 过滤 条件是Age大于18的User数据
         */

        //guava 使用filter方法
        long start1 = System.currentTimeMillis();
        Collection<User> result = Collections2.filter(list, x -> x.getAge() > 18);

        for (User item : result) {
            System.out.println(String.format("%s::%d", item.getName(), item.getAge()));
        }
        long end1 = System.currentTimeMillis();
        System.out.printf("guava耗时: %d(ms)\n", end1 - start1);

        // java8 stream(并行流) 实现
        long start2 = System.currentTimeMillis();
        Iterator<User> it = list.parallelStream().filter(x -> x.getAge() > 18).iterator();
        while (it.hasNext()) {
            User item = it.next();
            System.out.println(item.getName() + "--" + item.getAge());
        }
        long end2 = System.currentTimeMillis();
        System.out.printf("java8耗时: %d(ms)\n", end2 - start2);
    }

}

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
class User {

    /***
     * 用户名
     */
    private String Name;

    /**
     * 年龄
     */
    private int Age;
}
