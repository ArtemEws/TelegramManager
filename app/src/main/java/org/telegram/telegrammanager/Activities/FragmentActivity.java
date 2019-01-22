package org.telegram.telegrammanager.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import org.telegram.telegrammanager.Fragments.ChatListFragment;
import org.telegram.telegrammanager.Fragments.NewPostFragment;
import org.telegram.telegrammanager.R;

public class FragmentActivity extends AppCompatActivity {

    private static final int PERMISSIONS_WRITE_EXTERNAL_STORAGE = 0;
    private static final int PERMISSIONS_READ_EXTERNAL_STORAGE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        checkForPermissions();
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

        fragmentManager.beginTransaction().replace( R.id.main_fragment_container,
                new ChatListFragment(),
                ChatListFragment.TAG).commit();
    }

    public void checkForPermissions(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSIONS_READ_EXTERNAL_STORAGE);
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_WRITE_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
}
