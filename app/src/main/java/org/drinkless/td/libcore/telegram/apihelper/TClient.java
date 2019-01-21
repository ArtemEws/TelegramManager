package org.drinkless.td.libcore.telegram.apihelper;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;

import java.util.ArrayList;

public class TClient {



    public AuthorizationManager authManager;
    public Handler frontHandler;
    Client.ResultHandler apiHandler;
    // private User hostUser = null;

    Client client;
    TClient() {}
    
    public static TClient create(Handler updatesHandler) {

        TClient cl = new TClient();
        cl.authManager = new AuthorizationManager(cl);
        cl.apiHandler = new DefaultUpdatesHandler(cl);
        cl.frontHandler = updatesHandler;
        cl.client = Client.create(cl.apiHandler, null, null);

        String server = ProxyInfo.MTProto.server;
        int port = ProxyInfo.MTProto.port;
        String username = "rRkV7D";
        String password = "JcaK38";

        String secret = ProxyInfo.MTProto.secret;
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

    void getChat(long id, Handler fHandler) {
        ChatGetter.getChat(this, id, fHandler);
    }

    public void getChats(Handler fHandler) {
        ChatGetter.getChats(this, fHandler);
    }

    public void updateChat(Chat chat, Handler fHandler) {
        getChat(chat.chat.id, fHandler);
    }

    public void getChatMessages(Chat chat, Handler fHandler) {
        ChatGetter.getChatMessages(this, chat, fHandler);
    }

    public void sendMessage(Chat chat, String text, Handler fHandler) {
        MessageSender.sendTextMessage(this, chat, text, fHandler);
    }
}
