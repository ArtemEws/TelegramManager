package org.telegram.telegramchanelmaster.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.telegram.telegramchanelmaster.MainActivity;
import org.telegram.telegramchanelmaster.R;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorisation_one);

        EditText phoneNumberField = findViewById(R.id.phoneNumberField);

        Button continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNextActivity();
            }
        });
    }


    private void startNextActivity() {
        Intent intent = new Intent(LoginActivity.this, CodeActivity.class);
        startActivity(intent);
    }
}