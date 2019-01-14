package org.telegram.telegrammanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.telegram.telegrammanager.R;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorisation_one);

        Button continueButton = findViewById(R.id.continueButton);
        EditText phoneNumberField = findViewById(R.id.phoneNumberField);
        continueButton.setOnClickListener(continueButtonClickListener);
    }

    private View.OnClickListener continueButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startNextActivity();
        }
    };

    public void startNextActivity() {
        Intent intent = new Intent(LoginActivity.this, CodeActivity.class);
        startActivity(intent);
    }
}