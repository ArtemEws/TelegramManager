package org.drinkless.tdlib.apihelper;

import org.drinkless.tdlib.TdApi;

public class Chat implements Comparable{

    static String BASIC_GROUP = "BasicGroup";
    static String PRIVATE = "Private";
    static String SECRET = "Secret";
    static String SUPER_GROUP = "SuperGroup";
    static String NON_SPECIFIED = "NonSpecified";

    TdApi.Chat chat;
    SuperGroup superGroup;

    Chat() {
        this.chat = null;
        superGroup = null;
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

    public boolean isSuperGroupAdmin() {
        return isSuperGroup() && superGroup.isCurrentUserAdmin();
    }

    @Override
    public int compareTo(Object o) {
        Chat c = (Chat)o;
        if (chat.order < c.chat.order) return -1;
        if (chat.order > c.chat.order) return 1;

        return 0;
    }
}
