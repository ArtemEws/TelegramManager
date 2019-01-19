package org.drinkless.td.libcore.telegram.apihelper;

public interface Handler {

    void handle(String type, Object obj);
}
