package org.telegram.telegrammanager.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.drinkless.td.libcore.telegram.apihelper.Chat;
import org.drinkless.td.libcore.telegram.apihelper.Handler;
import org.drinkless.td.libcore.telegram.apihelper.MessageSender;
import org.telegram.telegrammanager.Fragments.ChatListFragment;
import org.telegram.telegrammanager.Fragments.NewPostFragment;
import org.telegram.telegrammanager.R;

import static org.drinkless.td.libcore.telegram.apihelper.MessageSender.sendTextMessage;
import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class FragmentActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        ActionBar bar = getSupportActionBar();

        final FragmentManager fragmentManager = getSupportFragmentManager();

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace( R.id.main_fragment_container,
                        new NewPostFragment()).commit();

//                fab.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View view) {
//                        Chat chat;
//                        EditText messageField = findViewById(R.id.message_text);
//                        sendTextMessage(tClient, chat, messageField.getText(), new Handler() {
//                            @Override
//                            public void handle(String type, Object obj) {
//                                if(type == "messageSent"){
//                                    fragmentManager.beginTransaction().replace( R.id.main_fragment_container,
//                                            new ChatListFragment(),
//                                            ChatListFragment.TAG).commit();
//                                }
//                            }
//                        });
//                    }
//                });
            }
        });

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().replace( R.id.main_fragment_container,
                                                        new ChatListFragment(),
                                                        ChatListFragment.TAG).commit();
        }
    }

//    TODO
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        
    }
}
