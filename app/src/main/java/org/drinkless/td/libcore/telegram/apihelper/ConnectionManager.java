package org.drinkless.td.libcore.telegram.apihelper;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;

public class ConnectionManager implements Client.ResultHandler {

    private int connectionState;

    public static final int WAITING_FOR_CONNECTION = 0;
    public static final int CONNECTED_TO_PROXY = 1;
    public static final int CONNECTING = 2;
    public static final int UPDATING = 3;
    public static final int READY = 4;

    private TClient host;

    public ConnectionManager(TClient host) {

        this.host = host;
    }

    public void onResult(TdApi.Object obj){
        TdApi.ConnectionState cs = (TdApi.ConnectionState)obj;

        switch (cs.getConstructor()) {
            case TdApi.ConnectionStateWaitingForNetwork.CONSTRUCTOR:
                connectionState = WAITING_FOR_CONNECTION;
                host.frontHandler.handle("connectionState", connectionState);
                break;
            case TdApi.ConnectionStateConnectingToProxy.CONSTRUCTOR:
                connectionState = CONNECTED_TO_PROXY;
                host.frontHandler.handle("connectionState", connectionState);
                break;
            case TdApi.ConnectionStateConnecting.CONSTRUCTOR:
                connectionState = CONNECTING;
                host.frontHandler.handle("connectionState", connectionState);
                break;
            case TdApi.ConnectionStateUpdating.CONSTRUCTOR:
                connectionState = UPDATING;
                host.frontHandler.handle("connectionState", connectionState);
                break;
            case TdApi.ConnectionStateReady.CONSTRUCTOR:
                connectionState = READY;
                host.frontHandler.handle("connectionState", connectionState);
                break;
            default:
                break;
        }
    }

}
