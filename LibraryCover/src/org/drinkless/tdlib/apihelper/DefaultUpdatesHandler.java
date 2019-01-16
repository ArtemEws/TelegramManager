package org.drinkless.tdlib.apihelper;

import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;

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
