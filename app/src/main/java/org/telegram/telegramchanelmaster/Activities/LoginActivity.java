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
        setContentView(R.layout.activity_authorization);

        Button getCodeButton = findViewById(R.id.getCodeButton);
        EditText phoneNumberField = findViewById(R.id.phoneNumberField);
        getCodeButton.setOnClickListener(getCodeButtonClickListener);
    }

    private View.OnClickListener getCodeButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            EditText phoneNumberField = findViewById(R.id.phoneNumberField);
//            phoneNumberField.setText("lol, it work (/ ^_^)/");
            startMainActivity();
        }
    };

    public void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}