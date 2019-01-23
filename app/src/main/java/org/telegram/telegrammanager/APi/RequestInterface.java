package org.telegram.telegrammanager.APi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("/sirius/delay-mess")
    Call<MessageData.DelayResponse> setDelay(@Body MessageData.DelayBody message);

    @POST("/sirius/set-token")
    Call<MessageData.TokenResponse> setChatToken(@Body MessageData.TokenBody body);
}