package org.telegram.telegrammanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.drinkless.td.libcore.telegram.apihelper.AuthorizationManager;
import org.drinkless.td.libcore.telegram.apihelper.Handler;
import org.telegram.telegrammanager.Helpers.TGClient;
import org.telegram.telegrammanager.MainActivity;
import org.telegram.telegrammanager.R;

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class LoginActivity extends AppCompatActivity {

    EditText phoneNumberField;

    static{
        System.loadLibrary("tdjni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorisation_one);

        Button continueButton = findViewById(R.id.continueButton);
        phoneNumberField = findViewById(R.id.phoneNumberField);
        continueButton.setOnClickListener(continueButtonClickListener);

        tClient.setUpdatesHandler(new LoginHandler());
    }


    private View.OnClickListener continueButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            tClient.authManager.sendCode(phoneNumberField.getText().toString());
        }
    };

    public void startNextActivity() {
        Intent intent = new Intent(LoginActivity.this, CodeActivity.class);
        startActivity(intent);
    }

    public class LoginHandler implements Handler {
        @Override

        public void handle(String type, Object obj) {


            if (type == "authState") {
                int state = (int) obj;

                if (state == AuthorizationManager.WAIT_CODE) {
                    Intent intent = new Intent(LoginActivity.this, CodeActivity.class);
                    startActivity(intent);

                } else if (type == "ERROR") {
                    System.out.println("Error occured");
                }
            }
        }
    }
}