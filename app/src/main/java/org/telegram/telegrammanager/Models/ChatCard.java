package org.telegram.telegrammanager.Models;

import org.drinkless.td.libcore.telegram.TdApi;

public class ChatCard {

    public TdApi.Chat chat;
    public String name;
    public Integer subs;
    public Integer photoId;

    public ChatCard(String name, Integer subs, int photoId) {
        this.name = name;
        this.subs = subs;
        this.photoId = photoId;
    }
}
