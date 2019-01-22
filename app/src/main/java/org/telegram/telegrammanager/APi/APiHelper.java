package org.telegram.telegrammanager.APi;

import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Arrays;

public class APiHelper {

    public static void setDelay(String message, String date){

        final String REQUEST_URL = "http://212.109.194.80";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(REQUEST_URL).build();

        MessageData.RequestBody body = new MessageData.RequestBody();
        body.message = message;
        body.date = date;

        RequestInterface ri = retrofit.create(RequestInterface.class);

        Call<MessageData.RequestResponse> call = ri.setDelay(body);

        call.enqueue(new Callback<MessageData.RequestResponse>() {
            @Override
            public void onResponse(Call<MessageData.RequestResponse> call, Response<MessageData.RequestResponse> response) {
                if (response.isSuccessful()) {
                    // tasks available
                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<MessageData.RequestResponse> call, Throwable t) {

            }
        });
    }
}
