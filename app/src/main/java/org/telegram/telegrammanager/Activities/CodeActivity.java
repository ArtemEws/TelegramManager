package org.telegram.telegrammanager.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.drinkless.td.libcore.telegram.apihelper.AuthorizationManager;
import org.telegram.telegrammanager.MainActivity;
import org.telegram.telegrammanager.R;

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class CodeActivity extends Activity {

    static{
        System.loadLibrary("tdjni");
    }

    EditText codeField;

    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_authorisation_code);

        Button codeButton = findViewById(R.id.codeButton);
        codeButton.setOnClickListener(codeButtonClickListener);
        codeField = findViewById(R.id.codeField);
        tClient.setUpdatesHandler(new AfterCodeHandler());
    }

    private View.OnClickListener codeButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            tClient.authManager.sendCode(codeField.getText().toString());
        }
    };

    public class AfterCodeHandler implements org.drinkless.td.libcore.telegram.apihelper.Handler {
        @Override
        public void handle(String type, Object obj) {
            if (type == "authState") {
                int state = (int) obj;

                if (state == AuthorizationManager.READY) {
                    Intent intent = new Intent(CodeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                if (state == AuthorizationManager.WAIT_PASSWORD){
                    Intent intent = new Intent(CodeActivity.this, PasswordActivity.class);
                    startActivity(intent);
                }
            } else if (type == "ERROR") {
                    Log.e("Authentication", "Error occured");
                }
        }
    }
}
