package org.drinkless.td.libcore.telegram.apihelper;

public interface TCondition {
    public boolean matches(String type, Object obj);
}
