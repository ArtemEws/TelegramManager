package org.drinkless.td.libcore.telegram.apihelper;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;

public class TClient {



    public AuthorizationManager authManager;
    public ConnectionManager connectionManager;

    public Handler frontHandler;
    Client.ResultHandler apiHandler;
    // private User hostUser = null;

    Client client;
    TClient() {}
    
    public static TClient create(Handler updatesHandler) {

        TClient cl = new TClient();
        cl.connectionManager = new ConnectionManager(cl);
        cl.authManager = new AuthorizationManager(cl);
        cl.apiHandler = new DefaultUpdatesHandler(cl);
        cl.frontHandler = updatesHandler;
        cl.client = Client.create(cl.apiHandler, null, null);

        String server = ProxyInfo.server;
        int port = ProxyInfo.port;
        String secret = ProxyInfo.secret;

        cl.addProxy(server, port, secret);
        return cl;
    }

    public void addProxy(String server, int port, String secret) {
        client.send(new TdApi.AddProxy(server, port, true, new TdApi.ProxyTypeMtproto(secret)), null);
    }

    public void setUpdatesHandler(Handler newHandler) {
        frontHandler = newHandler;
    }

    public Handler getUpdatesHandler() {
        return frontHandler;
    }

    public AuthorizationManager getAuthManager() {
        return authManager;
    }

    public void close() {
        client.send(new TdApi.Close(), null);
    }

    public void getChat(long id, Handler fHandler) {
        ChatGetter.getChat(this, id, fHandler);
    }

    public void getChats(Handler fHandler) {
        ChatGetter.getChats(this, fHandler);
    }

    public void updateChat(Chat chat, Handler fHandler) {
        getChat(chat.chat.id, fHandler);
    }

}
