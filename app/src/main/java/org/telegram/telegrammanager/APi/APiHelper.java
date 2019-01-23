package org.telegram.telegrammanager.APi;

import org.drinkless.td.libcore.telegram.apihelper.Chat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class APiHelper {

    public static void setDelay(String message, String date, String chatId){

        final String REQUEST_URL = "http://10.23.40.5:8297";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(REQUEST_URL).build();

        MessageData.DelayBody body = new MessageData.DelayBody();

        body.message = message;
        body.date = date;
        body.chatId = chatId;

        RequestInterface ri = retrofit.create(RequestInterface.class);

        Call<MessageData.DelayResponse> call = ri.setDelay(body);

        call.enqueue(new Callback<MessageData.DelayResponse>() {
            @Override
            public void onResponse(Call<MessageData.DelayResponse> call, Response<MessageData.DelayResponse> response) {
                if (response.isSuccessful()) {
                    response.body();
                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<MessageData.DelayResponse> call, Throwable t) {

            }
        });
    }

    public static void setChatToken(Chat chat, String token){

        final String REQUEST_URL = "http://10.23.40.5:8297";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(REQUEST_URL).build();

        MessageData.TokenBody body = new MessageData.TokenBody();

        body.chatId = String.valueOf(chat.getChatId());
        body.token = token;

        RequestInterface ri = retrofit.create(RequestInterface.class);

        Call<MessageData.TokenResponse> call = ri.setChatToken(body);

        call.enqueue(new Callback<MessageData.TokenResponse>() {
            @Override
            public void onResponse(Call<MessageData.TokenResponse> call, Response<MessageData.TokenResponse> response) {
                if (response.isSuccessful()) {
                    response.body();
                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<MessageData.TokenResponse> call, Throwable t) {

            }
        });
    }
}
