package org.drinkless.tdlib.apihelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MultiHandler implements Handler {

    private List<Pair<TCondition, Handler>> handlers = new ArrayList<>();


    @Override
    public void handle(String type, Object obj) {
        for (Pair<TCondition, Handler> pair : handlers) {
            if (pair.first.matches(type, obj)) {
                pair.second.handle(type, obj);
            }
        }
    }

    public void addHandler(TCondition t, Handler h) {
        Pair<TCondition, Handler> p = new Pair<>(t, h);
        handlers.add(p);
    }

    public void clear() {
        handlers.clear();
    }

    public void remove(TCondition t, Handler h) {
        Pair<TCondition, Handler> toRemove = null;
        for (Pair<TCondition, Handler> p : handlers) {
            if (new Pair<>(t, h).equals(p)) toRemove = p;
        }
        if (null != toRemove)
            handlers.remove(toRemove);
    }
}
