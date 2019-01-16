package org.telegram.telegrammanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import org.drinkless.td.libcore.telegram.apihelper.AuthorizationManager;
import org.drinkless.td.libcore.telegram.apihelper.Handler;
import org.telegram.telegrammanager.Activities.ChatListActivity;
import org.telegram.telegrammanager.Activities.GreetingActivity;

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_AUTH_STATE = "isAuthorised";
    private SharedPreferences mSettings;
    private static Boolean isAuthorised;
    private static String AUTH_EXEP_TAG = "Authorisation";

    static{
        System.loadLibrary("tdjni");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tClient.setUpdatesHandler(new LoginHandler());

    }

    public class LoginHandler implements Handler {
        @Override

        public void handle(String type, Object obj) {


            if (type == "authState") {
                int state = (int) obj;

                if (state == AuthorizationManager.READY) {
                    Log.i(AUTH_EXEP_TAG, "Auth completed");
                    Intent intent = new Intent(MainActivity.this, ChatListActivity.class);
                    startActivity(intent);
                } else if (state == AuthorizationManager.WAIT_PHONE_NUMBER) {
                    Intent intent = new Intent(MainActivity.this, GreetingActivity.class);
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

}
