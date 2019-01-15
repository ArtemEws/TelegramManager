package org.drinkless.td.libcore.telegram.apihelper;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;

public class DefaultUpdatesHandler implements Client.ResultHandler {
    TClient host;

    DefaultUpdatesHandler(TClient host) {
        this.host = host;
    }

    @Override
    public void onResult(TdApi.Object object) {
        switch (object.getConstructor()) {
            case TdApi.Error.CONSTRUCTOR:
                // error
                break;
            case TdApi.UpdateAuthorizationState.CONSTRUCTOR:
                host.authManager.onResult(( (TdApi.UpdateAuthorizationState)object).authorizationState);
        }
    }
}
