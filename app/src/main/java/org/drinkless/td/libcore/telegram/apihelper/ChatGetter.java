package org.drinkless.td.libcore.telegram.apihelper;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

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
                        Chat chat = new Chat((TdApi.Chat)object);
                        if (chat.isSuperGroup()) {
                            getSuperGroupFromChat(t, chat, ((type, obj) -> {
                                chat.superGroup = (SuperGroup)obj;
                                fHandler.handle("chat", chat);
                            }));
                        } else {
                            fHandler.handle("chat", chat);
                        }
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
                                    Collections.sort(chats);
                                    Collections.reverse(chats);
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

    static void getChatMessages(TClient t, Chat chat, Handler fHandler) {
        new Thread(()->{

            ArrayList<Message> messages = new ArrayList<>();
            AtomicLong lastMesId = new AtomicLong(0);
            while (messages.size() < 100 && lastMesId.get() != -1) {
                AtomicBoolean done = new AtomicBoolean(false);
                getSomeMessages(t, chat, lastMesId.get(), (type, obj) -> {
                    System.out.println(lastMesId.get());
                    ArrayList<Message> x = (ArrayList<Message>) obj;
                    if (x.size() == 0) {
                        lastMesId.set(-1);
                    } else {
                        lastMesId.set(x.get(x.size() - 1).message.id);
                    }

                    for (Message mes : x) messages.add(mes);

                    done.set(true);
                });

                while (!done.get()) {
                }
            }
            fHandler.handle("chatMessages", messages);

        }).start();
    }

    static void getSomeMessages(TClient t, Chat chat, long mid, Handler fHandler) {
        t.client.send(new TdApi.GetChatHistory(chat.chat.id, mid, 0, 100, false), object -> {
            if (object.getConstructor() == TdApi.Messages.CONSTRUCTOR) {
                TdApi.Messages messages = (TdApi.Messages)object;
                ArrayList<Message> ret = new ArrayList<>();
                if (messages.messages.length == 0) {
                    fHandler.handle("chatMessages", ret);
                } else {
                    for (TdApi.Message mes : messages.messages) {
                        ret.add(new Message(mes));
                    }
                    fHandler.handle("chatMessages", ret);
                }
            } else {
                fHandler.handle("ERROR", null);
            }
        });
    }
}
