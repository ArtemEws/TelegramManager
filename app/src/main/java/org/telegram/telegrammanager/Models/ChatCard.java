package org.telegram.telegrammanager.Models;

import org.drinkless.td.libcore.telegram.apihelper.Chat;

public class ChatCard {

    public Chat chat;
    public String name;
    public String lastMes;
    public Integer photoId;

    public ChatCard(Chat chat, String name, String lastMes, int photoId) {
        this.name = name;
        this.lastMes = lastMes;
        this.photoId = photoId;
        this.chat = chat;
    }
}
