package org.telegram.telegrammanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.drinkless.td.libcore.telegram.apihelper.AuthorizationManager;
import org.telegram.telegrammanager.Fragments.ChatListFragment;
import org.telegram.telegrammanager.Fragments.NewPostFragment;
import org.telegram.telegrammanager.MainActivity;
import org.telegram.telegrammanager.R;

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class FragmentActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Toolbar mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        ActionBar bar = getSupportActionBar();

        final FragmentManager fragmentManager = getSupportFragmentManager();

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> fragmentManager.beginTransaction().replace( R.id.main_fragment_container,
                new NewPostFragment(),NewPostFragment.TAG).addToBackStack(ChatListFragment.TAG).commit());
        Button exitButton  = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(view -> {
            tClient.authManager.logout();
            tClient.setUpdatesHandler(new AfterLogoutHandler());
        });
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().replace( R.id.main_fragment_container,
                                                        new ChatListFragment(),
                                                        ChatListFragment.TAG).commit();
        }


    }

    public class AfterLogoutHandler implements org.drinkless.td.libcore.telegram.apihelper.Handler {
        @Override
        public void handle(String type, Object obj) {
            if (type == "authState") {
                int state = (int) obj;

                if (state == AuthorizationManager.LOGGING_OUT) {
                    Intent intent = new Intent(FragmentActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            } else if (type == "ERROR") {
                Log.e("Authentication", "Error occured");
            }
        }
    }

}
