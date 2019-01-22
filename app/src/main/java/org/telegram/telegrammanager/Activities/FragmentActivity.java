package org.telegram.telegrammanager.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.telegram.telegrammanager.Fragments.ChatListFragment;
import org.telegram.telegrammanager.Fragments.NewPostFragment;
import org.telegram.telegrammanager.R;

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
                new NewPostFragment()).commit());

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().replace( R.id.main_fragment_container,
                                                        new ChatListFragment(),
                                                        ChatListFragment.TAG).commit();
        }
    }

//    TODO
    @Override
    public void onBackPressed() {
        final FragmentManager fragmentManager = getSupportFragmentManager();

        ((TextView)findViewById(R.id.header_text)).setText(R.string.app_name);

        fragmentManager.beginTransaction().replace( R.id.main_fragment_container,
                new ChatListFragment(),
                ChatListFragment.TAG).commit();
    }
}
