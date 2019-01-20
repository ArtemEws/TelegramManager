package org.drinkless.td.libcore.telegram.apihelper;


class Pair<T, E> {
    T first;
    E second;
    Pair(T t, E e) {
        this.first = t;
        this.second = e;
    }
    T getFirst() {
        return first;
    }
    E getSecond() {
        return second;
    }
    void setFirst(T t) {
        first = t;
    }
    void setSecond(E e) {
        second = e;
    }

    boolean equals(Pair<T, E> pair) {
        return second == pair.second && first == pair.first;
    }
}