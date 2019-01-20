package org.drinkless.tdlib;

import org.drinkless.tdlib.apihelper.*;

import java.util.ArrayList;
import java.util.Scanner;

class TestApp {

    // ВАЖНО
    static {
        System.loadLibrary("tdjni");
    }

    static Scanner scn;
    static TClient client;

    public static String prompt(String mes) {
        System.out.println(mes);

        return scn.nextLine();
    }

    static Handler innerHandler = null;

	public static void main(String args[]) {
        Log.setVerbosityLevel(3);
        Log.setFilePath("test.log");
//        Log.setVerbosityLevel(0);
        scn = new Scanner(System.in);
        client = TClient.create((type, obj) -> {
            if (type == "authState") {
                int state = (int)obj;

                if (state == AuthorizationManager.WAIT_PHONE_NUMBER) {

                    String phoneNumber = prompt("Enter phone number");
                    client.authManager.sendPhoneNumber(phoneNumber);

                } else if (state == AuthorizationManager.WAIT_CODE) {

                    String code = prompt("Enter code");
                    client.authManager.sendCode(code);

                } else if (state == AuthorizationManager.WAIT_PASSWORD) {

                    String password = prompt("Enter password");
                    client.authManager.sendPassword(password);

                } else if (state == AuthorizationManager.READY) {

                    System.out.println("You are in telegram");

                    client.setUpdatesHandler(new MultiHandler());

                    innerHandler = (itype, iobj) -> {
                        if (itype == "init") {
                            client.getChats(new Handler() {
                                @Override
                                public void handle(String type, Object obj) {
                                    if (type == "chats") {
                                        System.out.println("!Каналы:");
                                        ArrayList<Chat> chats = (ArrayList<Chat>)obj;

                                        for (int i = 0; i < chats.size(); i++) {
                                            Chat chat = chats.get(i);
                                            System.out.println(chat.getTitle() + " " + chat.getType());
                                        }

                                        innerHandler.handle("gotchats", chats);
                                    }
                                }
                            });
                        } else if (itype == "gotchats") {

                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                            ArrayList<Chat> chats = (ArrayList<Chat>)iobj;
//                            client.getChatMessages(chats.get(0), (ctype, cobj) -> {
//                                if (ctype == "chatMessages") {
//                                    ArrayList<Message> messages = (ArrayList<Message>)cobj;
//                                    for (Message message : messages) {
//                                        if (message.getMessageContent().isText())
//                                            System.out.println(message.getUserFrom().getShortName() + " " + message.getMessageContent().getText());
//                                    }
//                                }
//                            });
                            while (true) {
                                int num = Integer.parseInt(prompt("CHAT NUMBER"));
                                String text = prompt("Write message to " + chats.get(num).getTitle());
                                if (!text.equals("-")) {
                                    client.sendMessage(chats.get(num), text, (a, b)->{});
                                }
                            }
                        }
                    };

                    innerHandler.handle("init", null);



                }
            } else if (type == "ERROR") {
                System.out.println("Error occured");
            }
        });
    }
}
