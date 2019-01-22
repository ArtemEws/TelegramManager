package org.drinkless.td.libcore.telegram.apihelper;

import org.drinkless.td.libcore.telegram.TdApi;

public class Message {
    TdApi.Message message;
    User userFrom = null;

    Message(TdApi.Message message) {
        this.message = message;
    }

    public MessageContent getMessageContent() {
        return new MessageContent(message.content);
    }
}
