package org.telegram.telegrammanager.Models;

import org.telegram.telegrammanager.R;

import java.util.ArrayList;
import java.util.List;

public class ChatCard {
    public String name;
    public Integer subs;
    public Integer photoId;

    public ChatCard(String name, Integer subs, int photoId) {
        this.name = name;
        this.subs = subs;
        this.photoId = photoId;
    }

    private List<ChatCard> chats;

    // This method creates an ArrayList that has three Person objects
    // Checkout the project associated with this tutorial on Github if
    // you want to use the same images
    private void initializeData() {
        chats = new ArrayList<>();
        chats.add(new ChatCard("2ch", 3874165, R.drawable.logo));
        chats.add(new ChatCard("4ch", 245543, R.drawable.logo));
        chats.add(new ChatCard("Borsch", 98786, R.drawable.logo));
    }
}
