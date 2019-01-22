package org.telegram.telegrammanager;

public interface Callback<T> {
    void call(T message);
}
