package org.drinkless.tdlib.apihelper;

import org.drinkless.tdlib.TdApi;

public class MessageContent {
    TdApi.MessageContent messageContent;
    MessageContent(TdApi.MessageContent messageContent) {
        this.messageContent = messageContent;
    }

    public boolean isText() {
        return (messageContent.getConstructor() == TdApi.MessageText.CONSTRUCTOR);
    }

    public String getText() {
        if (isText())
            return ((TdApi.MessageText)messageContent).text.text;
        return null;
    }
}
