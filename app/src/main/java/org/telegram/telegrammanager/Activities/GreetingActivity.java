package org.telegram.telegrammanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import org.telegram.telegrammanager.R;

public class GreetingActivity extends AppCompatActivity {

    static{
        System.loadLibrary("tdjni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_authorisation_greeting);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(loginButtonClickListener);
    }

    private View.OnClickListener loginButtonClickListener = view -> startNextActivity();

    public void startNextActivity() {
        Intent intent = new Intent(GreetingActivity.this, PhoneNumberActivity.class);
        startActivity(intent);
    }
}
