package com.company;

import java.util.Objects;
import java.util.function.Function;

/**
 * Created by Main on 2015/10/12.
 */
@FunctionalInterface
interface TriConsumer<A, B, C> {

    void apply(A a, B b, C c);

    default <V> TriConsumer<A, B, C> andThen(
            Function<? super Void, ? extends V> after) {
        Objects.requireNonNull(after);
        return this::apply;
    }
}

