package org.telegram.telegramchanelmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import org.telegram.telegramchanelmaster.Activities.FirstActivity;
import org.telegram.telegramchanelmaster.Activities.LoginActivity;
import org.telegram.telegramchanelmaster.Helpers.LoginRunnable;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_AUTH_STATE = "isAuthorised";
    private SharedPreferences mSettings;
    private static Boolean isAuthorised;
    private static String AUTH_EXEP_TAG = "Authorisation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      check if user loged into app by thread, to avoid ui lags
//        mSettings = getPreferences(MODE_PRIVATE);
//        LoginRunnable checkForAuthorised = new LoginRunnable();
//        Thread loginThread = new Thread(checkForAuthorised);
//        try {
//            loginThread.join();
//        } catch (InterruptedException e) {
//            Log.e(AUTH_EXEP_TAG, "LoginThread didn't join to UI Thread on Start");
//        }
//        if(!checkForAuthorised.getIsAuthorised()){
            goToLogin();
//        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean(APP_PREFERENCES_AUTH_STATE, isAuthorised);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isAuthorised==null){
            mSettings = getPreferences(MODE_PRIVATE);
            LoginRunnable checkForAuthorised = new LoginRunnable();
            Thread loginThread = new Thread(checkForAuthorised);
            try {
                loginThread.join();
            } catch (InterruptedException e) {
                Log.e(AUTH_EXEP_TAG, "LoginThread didn't join to UI Thread on Resume");
            }
            if(!checkForAuthorised.getIsAuthorised()){
                goToLogin();
            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
//            SharedPreferences.Editor editor = mSettings.edit();
//            editor.putInt(APP_PREFERENCES_COUNTER, mCounter);
//            editor.apply();
    }

    public void goToLogin() {
        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
        startActivity(intent);
    }
}
