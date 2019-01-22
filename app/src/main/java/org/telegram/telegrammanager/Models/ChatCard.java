package org.telegram.telegrammanager.Models;

import org.drinkless.td.libcore.telegram.apihelper.Chat;

public class ChatCard {

    public Chat chat;
    public String name;
    public Integer subs;
    public Integer photoId;

    public ChatCard(Chat chat, String name, Integer subs, int photoId) {
        this.name = name;
        this.subs = subs;
        this.photoId = photoId;
        this.chat = chat;
    }
}
