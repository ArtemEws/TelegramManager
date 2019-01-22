package org.telegram.telegrammanager.APi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("/sirius/delay-mess")
    Call<MessageData.RequestResponse> setDelay(@Body MessageData.RequestBody message);
}