package com.corgi.test.java8.currying;

import java.util.stream.Stream;

/**
 * @title: TailCall
 * @description:
 * @author: dengmiao
 * @create: 2019-05-10 09:29
 **/
@FunctionalInterface
public interface TailCall<T> {

    /**
     * apply
     * @return
     */
    TailCall<T> apply();

    /**
     * isComplete
     * @return
     */
    default boolean isComplete() { return false; }

    /**
     * result
     * @return
     */
    default T result() { throw new Error("not implemented"); }

    /**
     * 流终止操作
     * @return
     */
    default T invoke() {
        return Stream.iterate(this, TailCall::apply)
                .filter(TailCall::isComplete)
                .findFirst()
                .get()
                .result();
    }
}
