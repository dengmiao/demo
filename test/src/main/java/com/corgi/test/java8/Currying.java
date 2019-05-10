package com.corgi.test.java8;

import com.corgi.test.java8.currying.TailCall;
import com.corgi.test.java8.currying.TailCalls;

import java.math.BigInteger;
import java.util.function.Function;

/**
 * @title: Currying
 * @description: 柯里化
 * @author: dengmiao
 * @create: 2019-05-09 16:45
 **/
public class Currying {

    public static void main(String[] args) {
        Function<Integer, Function<Integer, Function<Integer, Integer>>> currying = x -> y -> z -> (x + y) * z;

        System.out.println(currying.apply(4).apply(5).apply(6));

        try {
            System.out.println(factorialRec(20000));
        } catch(StackOverflowError ex) {
            System.out.println(ex);
        }

        System.out.println(factorialTailRec(1, 5).invoke());
        System.out.println(factorialTailRec(1, 200000000).invoke());
        System.out.println(BigFactorial.factorial(new BigInteger("200000000")));
    }

    /**
     * 阶乘 传统写法
     * @param number
     * @return
     */
    public static int factorialRec(final int number) {
        if (number == 1) {
            return number;
        }
        else {
            return number * factorialRec(number - 1);
        }
    }

    /**
     * 阶乘 尾递归写法
     * @param factorial
     * @param number
     * @return
     */
    public static TailCall<Integer> factorialTailRec(final int factorial, final int number) {
        if (number == 1) {
            return TailCalls.done(factorial);
        }
        else {
            return TailCalls.call(() -> factorialTailRec(factorial * number, number - 1));
        }
    }
}

class BigFactorial {
    public static BigInteger decrement(final BigInteger number) {
        return number.subtract(BigInteger.ONE);
    }

    public static BigInteger multiply(
            final BigInteger first, final BigInteger second) {
        return first.multiply(second);
    }

    final static BigInteger ONE = BigInteger.ONE;
    final static BigInteger FIVE = new BigInteger("5");
    final static BigInteger TWENTYK = new BigInteger("20000");
    //...

    private static TailCall<BigInteger> factorialTailRec(
            final BigInteger factorial, final BigInteger number) {
        if(number.equals(BigInteger.ONE)) {
            return TailCalls.done(factorial);
        }
        else {
            return TailCalls.call(() ->
                    factorialTailRec(multiply(factorial, number), decrement(number)));
        }
    }

    public static BigInteger factorial(final BigInteger number) {
        return factorialTailRec(BigInteger.ONE, number).invoke();
    }
}