package org.telegram.telegrammanager.Helpers;

import java.util.Random;

public class RandomStringGenerator {
    public static String generateString() {
        Random random = new Random();
        Integer length = 16;
        String characters =  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }
}
