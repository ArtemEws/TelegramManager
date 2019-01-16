package org.drinkless.tdlib.apihelper;

import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;

import java.util.ArrayList;

public class ChatGetter {

    /*
    All methods are async
     */


    static void getChat(TClient t, long id, Handler fHandler) {
        t.client.send(new TdApi.GetChat(id), new Client.ResultHandler() {
            @Override
            public void onResult(TdApi.Object object) {
                switch (object.getConstructor()) {
                    case TdApi.Error.CONSTRUCTOR:
                        break;
                    case TdApi.Chat.CONSTRUCTOR:
                        fHandler.handle("chat", new Chat((TdApi.Chat)object));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    static void getChats(TClient t, Handler fHandler) {

        Handler idsHandler = new Handler() {
            @Override
            public void handle(String type, Object obj) {
                ArrayList<Long> ids = (ArrayList<Long>)obj;
                ArrayList<Chat> chats = new ArrayList<>();
                for (int i = 0; i < ids.size(); i++) {
                    getChat(t, ids.get(i), new Handler() {
                        @Override
                        public void handle(String type, Object chatObject) {
                            if (type == "chat") {
                                Chat chat = (Chat) chatObject;
                                chats.add(chat);
                                if (chats.size() == ids.size()) {
                                    fHandler.handle("chats", chats);
                                }
                            }
                        }
                    });
                }
            }
        };

        t.client.send(new TdApi.GetChats(Long.MAX_VALUE, 0, 50), new Client.ResultHandler() {
            @Override
            public void onResult(TdApi.Object object) {

                ArrayList<Long> ids = new ArrayList<>();
                switch (object.getConstructor()) {
                    case TdApi.Chats.CONSTRUCTOR:
                        long[] chatIds = ((TdApi.Chats)object).chatIds;
                        for (long id : chatIds)
                            ids.add(id);
                        idsHandler.handle("chatsids", ids);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    static void getSuperGroupFromChat(TClient t, Chat chat, Handler fHandler) {
        if (!chat.isSuperGroup()) fHandler.handle("ERROR", null);
        long supergroupId = (((TdApi.ChatTypeSupergroup)chat.chat.type).supergroupId);

        t.client.send(new TdApi.GetSupergroup((int) supergroupId), new Client.ResultHandler() {
            @Override
            public void onResult(TdApi.Object object) {

                switch (object.getConstructor()) {
                    case TdApi.Error.CONSTRUCTOR:
                        fHandler.handle("ERROR", null);
                        break;
                    case TdApi.Supergroup.CONSTRUCTOR:
                        fHandler.handle("superGroup", new SuperGroup((TdApi.Supergroup)object));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
