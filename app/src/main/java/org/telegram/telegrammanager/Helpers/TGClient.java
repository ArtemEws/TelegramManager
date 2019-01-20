package org.telegram.telegrammanager.Helpers;

import android.content.Context;
import org.drinkless.td.libcore.telegram.apihelper.AuthorizationManager;
import org.drinkless.td.libcore.telegram.apihelper.Handler;
import org.drinkless.td.libcore.telegram.apihelper.TClient;
import org.telegram.telegrammanager.MainActivity;

public class TGClient {

    public static TClient tClient = TClient.create(new Handler() {
        @Override

        public void handle(String type, Object obj) {


    }});
}
