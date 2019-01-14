package com.madpixels.tgadmintools;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.drinkless.td.libcore.telegram.TdApi;
import org.drinkless.td.libcore.telegram.Client;

import java.io.IOError;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    Client client;

    String st = "pre";

    EditText phoneText;
    EditText codeText;
    Button phoneButton;
    Button codeButton;
    TextView infoText;

    class UpdatesHandler implements Client.ResultHandler {

        @Override
        public void onResult(TdApi.Object object) {
            switch (object.getConstructor()) {
                case TdApi.UpdateAuthorizationState.CONSTRUCTOR:
                    onAuth(((TdApi.UpdateAuthorizationState) object).authorizationState);
                    break;
                default:
                    break;

            }
        }

        public void onAuth(TdApi.AuthorizationState state) {
            switch (state.getConstructor()) {
                case TdApi.AuthorizationStateWaitTdlibParameters.CONSTRUCTOR:
                    Log.i("prog","Parameters");
                    TdApi.TdlibParameters parameters = new TdApi.TdlibParameters();
                    parameters.databaseDirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TBASE";
                    parameters.useMessageDatabase = true;
                    parameters.useSecretChats = true;
                    parameters.apiId = 94575;
                    parameters.apiHash = "a3406de8d171bb422bb6ddf3bbd800e2";
                    parameters.systemLanguageCode = "en";
                    parameters.deviceModel = "Desktop";
                    parameters.systemVersion = "Unknown";
                    parameters.applicationVersion = "1.0";
                    parameters.enableStorageOptimizer = true;
                    client.send(new TdApi.SetTdlibParameters(parameters), null);
                    break;
                case TdApi.AuthorizationStateWaitEncryptionKey.CONSTRUCTOR:
                    client.send(new TdApi.CheckDatabaseEncryptionKey(), null);
                    break;
                case TdApi.AuthorizationStateWaitPhoneNumber.CONSTRUCTOR:
                    st = "phone";
                    break;
                case TdApi.AuthorizationStateWaitCode.CONSTRUCTOR:
                    st = "code";
                    break;
                case TdApi.AuthorizationStateReady.CONSTRUCTOR:
                    st = "ready";
                    infoText.setText("УРААААААААА");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = Client.create(new UpdatesHandler(), null, null);
        String server = "91.188.243.144";
        int port = 9096;
        String username = "rRkV7D";
        String password = "JcaK38";
        Log.i("APPL", "hello");


        client.send(new TdApi.AddProxy(server, port, true, new TdApi.ProxyTypeHttp(username, password, false)), new Client.ResultHandler() {
            @Override
            public void onResult(TdApi.Object object) {
                Log.i("proxy", object.toString());
            }
        });

        phoneText = (EditText) findViewById(R.id.phoneEditText);
        codeText = (EditText) findViewById(R.id.CodeEditText);
        phoneButton = (Button) findViewById(R.id.PhoneButton);
        codeButton = (Button) findViewById(R.id.codeButton);

        infoText = (TextView) findViewById(R.id.textView2);

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (st == "phone") {
                    String phone = phoneText.getText().toString();
                    Log.i("phone", phone);
                    client.send(new TdApi.SetAuthenticationPhoneNumber(phone,false, false), null);
                }
            }
        });

        codeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (st == "code") {
                    String code = codeText.getText().toString();
                    Log.i("code", code);
                    client.send(new TdApi.CheckAuthenticationCode(code, "", ""), null);
                }
            }
        });



//        Log.w("ff", "PROXY");
    }

}
