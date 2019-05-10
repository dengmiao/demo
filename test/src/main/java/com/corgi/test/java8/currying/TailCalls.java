package com.corgi.test.java8.currying;

/**
 * @title: TailCalls
 * @description:
 * @author: dengmiao
 * @create: 2019-05-10 09:30
 **/
public class TailCalls {

    public static <T> TailCall<T> call(final TailCall<T> nextCall) {
        return nextCall;
    }
    public static <T> TailCall<T> done(final T value) {
        return new TailCall<T>() {
            @Override public boolean isComplete() { return true; }
            @Override public T result() { return value; }
            @Override public TailCall<T> apply() {
                throw new Error("end of recursion");
            }
        };
    }
}
