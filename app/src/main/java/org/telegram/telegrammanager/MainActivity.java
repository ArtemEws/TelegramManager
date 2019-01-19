package org.telegram.telegrammanager;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;
import org.drinkless.td.libcore.telegram.apihelper.*;
import org.telegram.telegrammanager.Activities.GreetingActivity;
import org.telegram.telegrammanager.Fragments.ChatListFragment;

import java.util.ArrayList;

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class MainActivity extends Activity {

    private static String AUTH_EXEP_TAG = "Authorisation";
    private static String CONNECTION_EXEP_TAG = "Connection";

    FragmentTransaction mainFragTrans;

    @NonNull
    ChatListFragment chatList = new ChatListFragment();

    static{
        System.loadLibrary("tdjni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragTrans = getFragmentManager().beginTransaction();
        mainFragTrans.replace(R.id.frgmnt, chatList);
        mainFragTrans.commit();

        tClient.setUpdatesHandler(new LoginHandler());
    }

//    public class ConnectionHandler implements Handler{
//
//        @Override
//        public void handle(String type, Object obj){
//
//            if (type == "connectionState") {
//                int state = (int) obj;
//                TextView text = findViewById(R.id.text);
//                switch (state){
//                    case ConnectionManager.READY:
//                        Log.i(CONNECTION_EXEP_TAG, "Connection completed successful");
//                        tClient.setUpdatesHandler(new LoginHandler());
//                        break;
//                    case ConnectionManager.CONNECTED_TO_PROXY:
//                        text.setText("connected to proxy");
//                        break;
//                    case ConnectionManager.WAITING_FOR_CONNECTION:
//                        text.setText("waiting for connection");
//                        break;
//                    case ConnectionManager.CONNECTING:
//                        text.setText("connecting...");
//                        break;
//                    case ConnectionManager.UPDATING:
//                        text.setText("updating...");
//                        break;
//                }
//
//            } else if (type == "ERROR") {
//                TextView text = findViewById(R.id.text);
//                text.setText("error");
//            }
//        }
//    }

    public class LoginHandler implements Handler {

        @Override
        public void handle(String type, Object obj) {


            if (type == "authState") {
                int state = (int) obj;

                if (state == AuthorizationManager.READY) {
                    Log.i(AUTH_EXEP_TAG, "Auth completed");

                    tClient.setUpdatesHandler(new MultiHandler());

                    ArrayList<Chat> chats;

                    tClient.getChats(new Handler() {
                        @Override
                        public void handle(String type, Object obj) {
                            if (type == "chats") {
                                System.out.println("Каналы:");
                                ArrayList<Chat> chats = (ArrayList<Chat>)obj;

                                for (int i = 0; i < chats.size(); i++) {
                                    Integer j = i;
                                    Chat chat = chats.get(i);

                                    TCondition cond = (ctype, cobj)->ctype == "file" && ((FileManager.File)cobj).getId() == chat.getPhotoFile().getId();
                                    Handler handler = (htype, hobj)->{
                                        FileManager.File file = (FileManager.File)hobj;
                                        tClient.updateChat(chat, new Handler() {
                                            @Override
                                            public void handle(String type, Object obj) {
                                                chats.set(j, (Chat)obj);
                                                Chat chat = chats.get(j);
                                                System.out.println(chat.getTitle() + " " + chat.getType() + chat.getPhotoFile().getLocalPath());
                                            }
                                        });
                                    };

                                    if (chat.isChannel()) {

                                        ((MultiHandler)tClient.frontHandler).addHandler(
                                                cond, handler
                                        );

                                        if (!chat.hasPhoto()) {
                                            System.out.println(chat.getTitle() + " " + chat.getType());
                                        } else if (chat.getPhotoFile().getLocalPath() == null || chat.getPhotoFile().getLocalPath().length() == 0)
                                            new FileManager(tClient).downloadFile(chat.getPhotoFile());
                                        else {
                                            System.out.println(chat.getTitle() + " " + chat.getType() + chat.getPhotoFile().getLocalPath());
                                        }
                                    }
                                }
                            }
                        }
                    });

                    mainFragTrans.replace(R.id.frgmnt, chatList);


                } else if (state == AuthorizationManager.WAIT_PHONE_NUMBER) {
                    Intent intent = new Intent(MainActivity.this, GreetingActivity.class);
                    startActivity(intent);
                }

            } else if (type == "ERROR") {
                System.out.println("Error occured");
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
