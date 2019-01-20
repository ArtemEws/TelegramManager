package org.telegram.telegrammanager.Helpers;

import org.drinkless.td.libcore.telegram.apihelper.Handler;
import org.drinkless.td.libcore.telegram.apihelper.TClient;

public class TGClient {

    public static TClient tClient = TClient.create(new Handler() {
        @Override

        public void handle(String type, Object obj) {


    }});
}
