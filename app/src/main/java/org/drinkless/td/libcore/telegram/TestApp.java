package org.drinkless.td.libcore.telegram;

import org.drinkless.td.libcore.telegram.apihelper.*;
import org.drinkless.tdlib.apihelper.TClient;
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

                    ArrayList<Chat> chats;

                    client.getChats(new Handler() {
                        @Override
                        public void handle(String type, Object obj) {
                            if (type == "chats") {
                                System.out.println("Каналы:");
                                ArrayList<Chat> chats = (ArrayList<Chat>)obj;

                                for (int i = 0; i < chats.size(); i++) {
                                    Integer j = i;
                                    Chat chat = chats.get(i);

                                    TCondition cond = (ctype, cobj)->ctype == "file" && ((FileManager.File)cobj).getId() == chat.getPhotoFile().getId();
                                    Handler handler = (htype, hobj)->{
                                        FileManager.File file = (FileManager.File)hobj;
                                        client.updateChat(chat, new Handler() {
                                            @Override
                                            public void handle(String type, Object obj) {
                                                chats.set(j, (Chat)obj);
                                                Chat chat = chats.get(j);
                                                System.out.println(chat.getTitle() + " " + chat.getType() + chat.getPhotoFile().getLocalPath());
                                            }
                                        });
                                    };

                                    if (chat.isChannel()) {

                                        ((MultiHandler)client.frontHandler).addHandler(
                                                cond, handler
                                        );

                                        if (!chat.hasPhoto()) {
                                            System.out.println(chat.getTitle() + " " + chat.getType());
                                        } else if (chat.getPhotoFile().getLocalPath() == null || chat.getPhotoFile().getLocalPath().length() == 0)
                                            new FileManager(client).downloadFile(chat.getPhotoFile());
                                        else {
                                            System.out.println(chat.getTitle() + " " + chat.getType() + chat.getPhotoFile().getLocalPath());
                                        }
                                    }
                                }
                            }
                        }
                    });

                }
            } else if (type == "ERROR") {
                System.out.println("Error occured");
            }
        });
    }
}
