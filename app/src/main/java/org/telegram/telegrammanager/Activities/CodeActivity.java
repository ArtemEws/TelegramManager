package org.telegram.telegrammanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.drinkless.td.libcore.telegram.apihelper.AuthorizationManager;
import org.telegram.telegrammanager.R;

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class CodeActivity extends AppCompatActivity {

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
                    Intent intent = new Intent(CodeActivity.this, ChatListActivity.class);
                    startActivity(intent);
                } else if (type == "ERROR") {
                    System.out.println("Error occured");
                }
            }
        }
    }
}
