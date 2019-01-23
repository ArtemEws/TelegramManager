package org.telegram.telegrammanager.APi;

public class MessageData {

    public static class DelayBody {
        public String message;
        public String date;
        public String chatId;
    }

    public static class DelayResponse {
        public String status;
    }

    public static class TokenBody {
        public String chatId;
        public String token;
    }

    public static class TokenResponse {
        public String status;
    }
}
