package org.telegram.telegrammanager.Helpers;

import android.util.Log;
import org.drinkless.td.libcore.telegram.apihelper.Chat;
import org.drinkless.td.libcore.telegram.apihelper.Handler;
import org.drinkless.td.libcore.telegram.apihelper.Message;

import java.util.ArrayList;

import static org.drinkless.td.libcore.telegram.apihelper.ChatGetter.*;
import static org.drinkless.td.libcore.telegram.apihelper.MessageSender.sendTextMessage;
import static org.telegram.telegrammanager.APi.APiHelper.setChatToken;
import static org.telegram.telegrammanager.APi.APiHelper.setDelay;
import static org.telegram.telegrammanager.Helpers.RandomStringGenerator.generateString;
import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class BotManager {

    static final long FATHER_ID = 93372553;
    private static Chat bot;
    static final String ERROR_BOT_CREATE_TAG = "Bot create error:";
    static final String SUCCESS_BOT_CREATE_TAG = "Bot created";
    static String token;
    static Boolean needUsername = true;
    public static void botManager() {
        getChat(tClient, FATHER_ID, (type, obj) -> {
            if (type == "chat") {
                bot = (Chat) obj;
            }
        });
    }

    synchronized public static void createNewBot(Chat group) {

        sendTextMessage(tClient, bot, "/newbot", (type, obj) -> {
            if (type == "messageSent") {
                Log.i(SUCCESS_BOT_CREATE_TAG, "Step 1");
            } else {
                Log.e(ERROR_BOT_CREATE_TAG, "Woopsy");
            }
        });

        sendTextMessage(tClient, bot, group.getTitle(), (type, obj) -> {
            if (type == "messageSent") {
                Log.i(SUCCESS_BOT_CREATE_TAG, "Step 2");
            } else {
                Log.e(ERROR_BOT_CREATE_TAG, "Woopsy");
            }
        });

        Integer counter = 0;
        while (needUsername && counter < 3) {
            sendTextMessage(tClient, bot, generateString() + "_bot", (type, obj) -> {
                if (type == "messageSent") {
                    Log.i(SUCCESS_BOT_CREATE_TAG, "Step 3");
                } else {
                    Log.e(ERROR_BOT_CREATE_TAG, "Woopsy");
                }
            });

            getLastMessage(tClient, bot, (type, obj) -> {
                if (type == "lastMessage") {
                    Message message1 = (Message) obj;
                    String message = message1.getMessageContent().getText();
                    token = message.substring(420, 465);
                    String sbs = message.substring(0, 4);
                    needUsername = !sbs.equals("Done");
                }
            });

            counter++;
        }

        if(counter<4){
            getLastMessage(tClient, bot, (type, obj) -> {
                if (type == "lastMessage") {
                    Message message1 = (Message) obj;
                    token = message1.getMessageContent().getText().substring(420, 465);
                }
            });

            setChatToken(bot, token);
        }
    }

    public static void sendDelayedMessage(String message, String date, Chat chat) {


        setDelay(message, date, String.valueOf(chat.getChatId()));
    }

}
