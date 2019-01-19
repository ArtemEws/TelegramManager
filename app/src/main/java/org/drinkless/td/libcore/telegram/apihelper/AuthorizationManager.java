package org.drinkless.td.libcore.telegram.apihelper;

import android.os.Build;
import android.os.Environment;
import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;

public class AuthorizationManager implements Client.ResultHandler {
    private int authState;
    public static final int SET_PARAMETERS = 0;
    public static final int WAIT_ENCRYPION_KEY = 1;
    public static final int WAIT_PHONE_NUMBER = 2;
    public static final int WAIT_CODE = 3;
    public static final int WAIT_PASSWORD = 4;
    public static final int READY = 5;
    public static final int LOGGING_OUT = 6;
    public static final int CLOSING = 7;
    public static final int CLOSED = 8;

    private TClient host;

    public AuthorizationManager(TClient host) {
        authState = SET_PARAMETERS;
        this.host = host;
    }

    public int getAuthState() {
        return authState;
    }

    public void onResult(TdApi.Object obj) {
        switch (obj.getConstructor()) {
            case TdApi.Error.CONSTRUCTOR:
                host.frontHandler.handle("ERROR", null);
                return;
            // TODO допилить когда неправильный пароль
        }

        TdApi.AuthorizationState as = (TdApi.AuthorizationState)obj;

        switch (as.getConstructor()) {
            case TdApi.AuthorizationStateWaitTdlibParameters.CONSTRUCTOR:
                TdApi.TdlibParameters parameters = new TdApi.TdlibParameters();
                parameters.databaseDirectory = Environment.getExternalStorageDirectory().getAbsolutePath()+"/TelegramManager";
                parameters.useMessageDatabase = true;
                parameters.useSecretChats = true;
                parameters.apiId = 648877;
                parameters.apiHash = "52578e23234a38b51542066da936acd1";
                parameters.systemLanguageCode = "ru";
                parameters.deviceModel = Build.MODEL;
                parameters.systemVersion = String.valueOf(Build.VERSION.SDK_INT);
                parameters.applicationVersion = "1.0";
                parameters.enableStorageOptimizer = true;
                host.client.send(new TdApi.SetTdlibParameters(parameters), this);
                break;
            case TdApi.AuthorizationStateWaitEncryptionKey.CONSTRUCTOR:
                authState = WAIT_ENCRYPION_KEY;
                host.client.send(new TdApi.CheckDatabaseEncryptionKey(), this);
                break;
            case TdApi.AuthorizationStateWaitPhoneNumber.CONSTRUCTOR:
                authState = WAIT_PHONE_NUMBER;
                host.frontHandler.handle("authState", authState);
                break;
            case TdApi.AuthorizationStateWaitCode.CONSTRUCTOR:
                authState = WAIT_CODE;
                host.frontHandler.handle("authState", authState);
                break;
            case TdApi.AuthorizationStateWaitPassword.CONSTRUCTOR:
                authState = WAIT_PASSWORD;
                host.frontHandler.handle("authState", authState);
            case TdApi.AuthorizationStateReady.CONSTRUCTOR:
                authState = READY;
                host.frontHandler.handle("authState", authState);
                break;
            case TdApi.AuthorizationStateLoggingOut.CONSTRUCTOR:
                authState = LOGGING_OUT;
                host.frontHandler.handle("authState", authState);
                break;
            case TdApi.AuthorizationStateClosed.CONSTRUCTOR:
                authState = CLOSED;
                host.frontHandler.handle("authState", authState);
                break;
            default:
                break;
        }
    }

    public void sendPhoneNumber(String phoneNumber) {
        if (getAuthState() == WAIT_PHONE_NUMBER) {
            host.client.send(new TdApi.SetAuthenticationPhoneNumber(phoneNumber, false, false), this);
        }
    }

    public void sendCode(String code) {
        if (getAuthState() == WAIT_CODE) {
            host.client.send(new TdApi.CheckAuthenticationCode(code, "", ""), this);
        }
    }

    public void sendPassword(String password) {
        if (getAuthState() == WAIT_PASSWORD) {
            host.client.send(new TdApi.CheckAuthenticationPassword(password), this);
        }
    }

    public void logout() {
        if (getAuthState() == READY) {
            host.client.send(new TdApi.LogOut(), this);
        }
    }

}
