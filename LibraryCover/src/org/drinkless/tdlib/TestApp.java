package org.drinkless.tdlib;

import org.drinkless.tdlib.apihelper.AuthorizationManager;
import org.drinkless.tdlib.apihelper.TClient;
import org.drinkless.tdlib.apihelper.Handler;

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
                    String s = prompt("Logout (lo) or quit (q)?");
                    if (s == "lo") {
                       client.authManager.logout();
                    } else if (s == "q") {
                        client.close();
                    }

                }
            } else if (type == "ERROR") {
                System.out.println("Error occured");
            }
        });
    }
}
