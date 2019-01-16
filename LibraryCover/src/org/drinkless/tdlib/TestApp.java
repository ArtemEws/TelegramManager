package org.drinkless.tdlib;

import org.drinkless.tdlib.apihelper.AuthorizationManager;
import org.drinkless.tdlib.apihelper.Chat;
import org.drinkless.tdlib.apihelper.TClient;
import org.drinkless.tdlib.apihelper.Handler;

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
        boolean closed[] = new boolean[1];
        closed[0] = false;
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

                    ArrayList<Chat> chats;

                    client.getChats(new Handler() {
                        @Override
                        public void handle(String type, Object obj) {
                            if (type == "chats") {
                                System.out.println("Каналы:");
                                ArrayList<Chat> chats = (ArrayList<Chat>)obj;
                                for (Chat chat : chats) {
                                    if (chat.isChannel())
                                        System.out.println(chat.getTitle() + " " + chat.getType());
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
