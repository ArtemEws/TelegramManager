package org.telegram.telegrammanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import org.telegram.telegrammanager.MainActivity;
import org.telegram.telegrammanager.R;

public class CodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_authorisation_two);


        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(startButtonClickListener);
    }

    private View.OnClickListener startButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startNextActivity();
        }
    };

    public void startNextActivity() {
        Intent intent = new Intent(CodeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
