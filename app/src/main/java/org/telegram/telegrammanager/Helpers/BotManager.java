package org.telegram.telegrammanager.Helpers;

import android.util.Log;
import android.widget.Toast;
import org.drinkless.td.libcore.telegram.apihelper.Chat;
import org.drinkless.td.libcore.telegram.apihelper.Handler;
import org.drinkless.td.libcore.telegram.apihelper.Message;

import java.util.ArrayList;

import static org.drinkless.td.libcore.telegram.apihelper.ChatGetter.getChat;
import static org.drinkless.td.libcore.telegram.apihelper.ChatGetter.getChatAdmins;
import static org.drinkless.td.libcore.telegram.apihelper.ChatGetter.getLastMessage;
import static org.drinkless.td.libcore.telegram.apihelper.MessageSender.sendTextMessage;
import static org.telegram.telegrammanager.APi.APiHelper.setDelay;
import static org.telegram.telegrammanager.Helpers.RandomStringGenerator.generateString;
import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class BotManager {

    static final long FATHER_ID = 93372553;
    private static Chat chat;
    static final String ERROR_BOT_CREATE_TAG = "Bot create error:";
    static final String SUCCESS_BOT_CREATE_TAG = "Bot created";

    public static void botManager() {
        getChat(tClient, FATHER_ID, new Handler() {
            @Override
            public void handle(String type, Object obj) {
                if (type == "chat") {
                    chat = (Chat) obj;
                }
            }
        });
    }

    synchronized public static void createNewBot(Chat chat) {
        sendTextMessage(tClient, chat, "/newbot", new Handler() {
            @Override
            public void handle(String type, Object obj) {
                if (type == "messageSent") {
                    Log.i(SUCCESS_BOT_CREATE_TAG, "Step 1");
                } else {
                    Log.e(ERROR_BOT_CREATE_TAG, "Woopsy");
                }
            }
        });

        sendTextMessage(tClient, chat, chat.getTitle(), new Handler() {
            @Override
            public void handle(String type, Object obj) {
                if (type == "messageSent") {
                    Log.i(SUCCESS_BOT_CREATE_TAG, "Step 2");
                } else {
                    Log.e(ERROR_BOT_CREATE_TAG, "Woopsy");
                }
            }
        });

        String message = "";

        while (!message.contains("Done")) {
            sendTextMessage(tClient, chat, java.util.UUID.randomUUID().toString() + "_bot", new Handler() {
                @Override
                public void handle(String type, Object obj) {
                    if (type == "messageSent") {
                        Log.i(SUCCESS_BOT_CREATE_TAG, "Step 2");
                    } else {
                        Log.e(ERROR_BOT_CREATE_TAG, "Woopsy");
                    }
                }
            });


        }

        getLastMessage(tClient, chat, new Handler() {
            @Override
            public void handle(String type, Object obj) {
                if (type == "lastMessage") {
                    Message message = (Message) obj;
                    String text = message.getMessageContent().getText().substring(420, 465);
                }
            }
        });
    }

    public static void sendDelayedMessage(String message, String date, Chat chat){
        getChatAdmins(tClient, chat, (type, obj) -> {
            if (type == "adminIds"){
                ArrayList<Integer> adminIds = (ArrayList<Integer>)obj;
            }
        });

        setDelay(message, date, String.valueOf(chat.getChatId()));
    }

    public static void testFunc(){
        getLastMessage(tClient, chat, new Handler() {
            @Override
            public void handle(String type, Object obj) {
                if (type == "lastMessage") {
                    Message message = (Message) obj;
                    String text = message.getMessageContent().getText().substring(420, 465);
                }
            }
        });
    }
}
