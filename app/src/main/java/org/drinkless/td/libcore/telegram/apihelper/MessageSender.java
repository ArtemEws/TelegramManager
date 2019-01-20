package org.drinkless.td.libcore.telegram.apihelper;

import org.drinkless.td.libcore.telegram.TdApi;

public class MessageSender {
    static void sendTextMessage(TClient t, Chat chat, String text, Handler fHandler) {
        TdApi.FormattedText formattedText = new TdApi.FormattedText(text, null);
        TdApi.InputMessageContent mcontent = new TdApi.InputMessageText(formattedText, false, false);
        t.client.send(new TdApi.SendMessage(chat.chat.id, 0, false,
                        false, null, mcontent),
                (object -> {
                    if (object.getConstructor() == TdApi.Message.CONSTRUCTOR) {
                        fHandler.handle("messageSent", null);
                    }
                }));
    }
}
