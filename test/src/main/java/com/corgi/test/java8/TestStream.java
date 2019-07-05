package com.corgi.test.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @title: TestStream
 * @description: 流编程
 * @author: dengmiao
 * @create: 2019-05-09 10:27
 **/
public class TestStream {

    /**
     * 初始化用户集合
     */
    static List<UserInfo> list = new ArrayList<UserInfo>(){
        {
            add(new UserInfo("tom", 18));
            add(new UserInfo("bob", 11));
            add(new UserInfo("Joan", 20));
            add(new UserInfo("John", 22));
            add(new UserInfo("Mike", 9));
        }
    };

    public static void main(String[] args) {
        int[] nums = {1,5,2,6,9,3,7,8,4};
        // 内部迭代
        int sum1= IntStream.of(nums).sum();
        // map 中间操作, 返回stream的操作
        int sum2 = IntStream.of(nums).map(i -> i * 2).sum();
        System.out.printf("原始之和: %d, 乘2之和: %d", sum1, sum2);

        // 惰性求值, 终止操作没调用的情况下中间操作不会进行
        IntStream intStream = IntStream.of(nums).map(TestStream::operate2num);
        System.out.println(intStream.sum());

        /**
         * 流的创建
         */
        // 从集合创建
        list.stream();
        // 从数组创建
        Arrays.stream(new int[]{1,2,3});
        // 数字流
        IntStream.of(1,2,3);
        IntStream.rangeClosed(1,10);
        // Random创建
        new Random().ints().limit(10);
        // 手动创建
        Stream.generate(() -> new Random().nextInt()).limit(10);

        /**
         * 流的中间操作
         */
        String str = "this is a test program";
        Stream.of(str.split(" ")).filter(s -> s.length()> 0).map(s -> s + ":" + s.length()).forEach(System.out::println);
        System.out.println("-------------");
        // flatMap a->b 最终得到a元素里所有b属性的集合
        // IntStream/LongStream 等基本类型的流不是Stream的子类, 需要boxed进行装箱
        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(i -> System.out.println((char)i.intValue()));
        System.out.println("-------------");
        // peek 用于debug是个中间操作, forEach是终止操作
        Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);
        System.out.println("-------------");
        // limit 主要用于无限流
        new Random().ints().filter(i -> i > 100 && i < 10000000).limit(10).forEach(System.out::println);

        System.out.println("-------------");
        // 并行流
        str.chars().parallel().forEach(i -> System.out.print((char)i));
        System.out.println("\n-------------");
        // forEachOrdered 并行流中保证顺序
        str.chars().parallel().forEachOrdered(i -> System.out.print((char)i));
        System.out.println("\n-------------");
        // 收集到list
        List<String> stringList = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(stringList);
        System.out.println("\n-------------");
        // reduce拼接字符串
        Optional<String> optional1 = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "#" + s2);
        System.out.println(optional1.orElse(""));
        System.out.println("\n-------------");
        // 带初始化值的reduce
        String optional2 = Stream.of(str.split(" ")).reduce("init", (s1, s2) -> s1 + "#" + s2);
        System.out.println(">>>>" + optional2);
        System.out.println("\n-------------");
        // max
        Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> s1.length() - s2.length());
        System.out.println(max.get());
        System.out.println("\n-------------");
        // findFirst 短路操作
        OptionalInt first = new Random().ints().findFirst();
        System.out.println(first.getAsInt());
    }

    public static int operate2num(int i){
        System.out.println("执行方法调用");
        return i * 2;
    }
}

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
class UserInfo {

    /***
     * 用户名
     */
    private String Name;

    /**
     * 年龄
     */
    private int Age;
}
