package org.drinkless.td.libcore.telegram.apihelper;

import org.drinkless.td.libcore.telegram.TdApi;

public class Chat {

    public static String BASIC_GROUP = "BasicGroup";
    public static String PRIVATE = "Private";
    public static String SECRET = "Secret";
    public static String SUPER_GROUP = "SuperGroup";
    public static String NON_SPECIFIED = "NonSpecified";

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

    public boolean isSuperGroup() {
        return chat.type.getConstructor() == TdApi.ChatTypeSupergroup.CONSTRUCTOR;
    }

    public boolean isChannel() {
        if (chat.type.getConstructor() == TdApi.ChatTypeSupergroup.CONSTRUCTOR) {
            return ((TdApi.ChatTypeSupergroup)chat.type).isChannel;
        }
        return false;
    }

    public FileManager.File getPhotoFile() {
        return new FileManager.File(chat.photo.small);
    }

    public boolean hasPhoto() {
        return chat.photo != null;
    }
}
