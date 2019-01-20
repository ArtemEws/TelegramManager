package org.drinkless.td.libcore.telegram.apihelper;

import org.drinkless.td.libcore.telegram.TdApi;

public class UserGetter {
    static void getUserbyId(TClient t, int id, Handler fHandler) {
        t.client.send(new TdApi.GetUser(id), (object -> {
            if (object.getConstructor() == TdApi.User.CONSTRUCTOR) {
                TdApi.User user = (TdApi.User)object;
                fHandler.handle("user", new User(user));
            } else if (object.getConstructor() == TdApi.Error.CONSTRUCTOR) {
                fHandler.handle("ERROR", null);
            }
        }));
    }
}
