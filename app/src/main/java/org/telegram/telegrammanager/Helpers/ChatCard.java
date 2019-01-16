package org.telegram.telegrammanager.Helpers;

import org.telegram.telegrammanager.R;

import java.util.ArrayList;
import java.util.List;

class ChatCard {
    String name;
    String subs;
    int photoId;

    ChatCard(String name, String subs, int photoId) {
        this.name = name;
        this.subs = subs;
        this.photoId = photoId;
    }

    private List<ChatCard> persons;

    // This method creates an ArrayList that has three Person objects
    // Checkout the project associated with this tutorial on Github if
    // you want to use the same images
    private void initializeData() {
        persons = new ArrayList<>();
        persons.add(new ChatCard("Emma Wilson", "23 years old", R.drawable.logo));
        persons.add(new ChatCard("Lavery Maiss", "25 years old", R.drawable.logo));
        persons.add(new ChatCard("Lillie Watts", "35 years old", R.drawable.logo));
    }
}
