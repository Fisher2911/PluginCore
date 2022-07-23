package io.github.fisher2911.plugincore.util;

import io.github.fisher2911.plugincore.database.annotation.Table;

public class Pair<T, Z> {

    private final T first;
    private final Z second;

    private Pair(T first, Z second) {
        this.first = first;
        this.second = second;
    }

    public static <T, Z> Pair<T, Z> of(T first, Z second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public Z getSecond() {
        return second;
    }

}
