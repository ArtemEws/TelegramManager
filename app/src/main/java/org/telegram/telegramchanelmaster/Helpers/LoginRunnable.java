package org.telegram.telegramchanelmaster.Helpers;

import android.content.Intent;
import android.content.SharedPreferences;

import java.util.concurrent.Callable;

import static android.content.Context.MODE_PRIVATE;

public class LoginRunnable implements Runnable {

    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_AUTH_STATE = "isAuthorised";
    private SharedPreferences mSettings;
    private static Boolean isAuthorised = false;

    @Override
    public void run(){
        if (mSettings.contains(APP_PREFERENCES_AUTH_STATE)) {
            this.isAuthorised = mSettings.getBoolean(APP_PREFERENCES_AUTH_STATE, false);
        }
    }

    public boolean getIsAuthorised(){
        return this.isAuthorised;
    }
}
