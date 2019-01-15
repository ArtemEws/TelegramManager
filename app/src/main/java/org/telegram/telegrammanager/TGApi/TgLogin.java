package org.telegram.telegrammanager.TGApi;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;
import org.telegram.telegrammanager.R;

public class TgLogin implements Client.ResultHandler {

    String st = "pre";

    EditText phoneText;
    EditText codeText;
    Button phoneButton;
    Button codeButton;
    TextView infoText;
    Client client;

    public void TgLogin(Client client,
                        EditText phoneText,
                        EditText codeText,
                        Button phoneButton,
                        Button codeButton) {

        this.client = client;
        this.phoneText = phoneText;
        this.codeText = codeText;
        this.phoneButton = codeButton;
        this.codeButton = codeButton;
    }

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
                Log.i("prog", "Parameters");
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