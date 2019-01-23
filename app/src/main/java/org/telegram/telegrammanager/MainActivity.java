package org.telegram.telegrammanager;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import org.drinkless.td.libcore.telegram.apihelper.*;
import org.telegram.telegrammanager.Activities.FragmentActivity;
import org.telegram.telegrammanager.Activities.GreetingActivity;
import org.telegram.telegrammanager.Fragments.ChatListFragment;

import java.util.ArrayList;

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class MainActivity extends Activity {

    private static final String AUTH_EXEP_TAG = "Authorisation";
    private static final String CONNECTION_EXEP_TAG = "Connection";
    private static final int PERMISSIONS_WRITE_EXTERNAL_STORAGE = 0;
    private static final int PERMISSIONS_READ_EXTERNAL_STORAGE = 1;

    FragmentTransaction mainFragTrans;

    @NonNull
    ChatListFragment chatList = new ChatListFragment();

    static{
        System.loadLibrary("tdjni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tClient.setUpdatesHandler(new LoginHandler());
        if (savedInstanceState == null) {
            Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
            startActivity(intent);
        }
    }

    public class LoginHandler implements Handler {

        @Override
        public void handle(String type, Object obj) {


            if (type == "authState") {
                int state = (int) obj;

                if (state == AuthorizationManager.READY) {
                    Log.i(AUTH_EXEP_TAG, "Auth completed");

                    tClient.setUpdatesHandler(new MultiHandler());
                    Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                } else if (state == AuthorizationManager.WAIT_PHONE_NUMBER) {
                    Intent intent = new Intent(MainActivity.this, GreetingActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            } else if (type == "ERROR") {
                System.out.println("Error occured");
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
