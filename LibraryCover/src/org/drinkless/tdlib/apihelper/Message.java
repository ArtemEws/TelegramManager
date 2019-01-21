package org.drinkless.tdlib.apihelper;

import org.drinkless.tdlib.TdApi;

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
