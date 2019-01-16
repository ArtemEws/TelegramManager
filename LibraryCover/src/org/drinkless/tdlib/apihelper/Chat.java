package org.drinkless.tdlib.apihelper;

import org.drinkless.tdlib.TdApi;

public class Chat {

    static String BASIC_GROUP = "BasicGroup";
    static String PRIVATE = "Private";
    static String SECRET = "Secret";
    static String SUPER_GROUP = "SuperGroup";
    static String NON_SPECIFIED = "NonSpecified";

    TdApi.Chat chat;

    Chat() {
        this.chat = null;
    }

    Chat(TdApi.Chat chat) {
        this.chat = chat;
    }

    public String getTitle() {
        return chat.title;
    }

    public String getType() {
        TdApi.ChatType type = chat.type;
        switch (type.getConstructor()) {
            case TdApi.ChatTypeBasicGroup.CONSTRUCTOR:
                return BASIC_GROUP;
            case TdApi.ChatTypePrivate.CONSTRUCTOR:
                return PRIVATE;
            case TdApi.ChatTypeSecret.CONSTRUCTOR:
                return SECRET;
            case TdApi.ChatTypeSupergroup.CONSTRUCTOR:
                return SUPER_GROUP;
            default:
                return NON_SPECIFIED;
        }
    }

    public String getChatPhoto() {
        if (chat.photo == null)
            return null;

        return chat.photo.small.local.path;
    }

    public boolean isSuperGroup() {
        return chat.type.getConstructor() == TdApi.ChatTypeSupergroup.CONSTRUCTOR;
    }

    public boolean isChannel() {
        if (chat.type.getConstructor() == TdApi.ChatTypeSupergroup.CONSTRUCTOR) {
            return ((TdApi.ChatTypeSupergroup)chat.type).isChannel;
        }
        return false;
    }
}
