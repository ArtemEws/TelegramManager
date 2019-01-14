package org.telegram.telegramchanelmaster.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import org.telegram.telegramchanelmaster.MainActivity;
import org.telegram.telegramchanelmaster.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_authorisation_home);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(loginButtonClickListener);
    }

    private View.OnClickListener loginButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startNextActivity();
        }
    };

    public void startNextActivity() {
        Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
