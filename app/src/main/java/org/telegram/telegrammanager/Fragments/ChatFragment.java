package org.telegram.telegrammanager.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.drinkless.td.libcore.telegram.apihelper.Chat;
import org.telegram.telegrammanager.R;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private static Context context;
    public Integer id;
    public ArrayList<Chat> chat;
    public ChatFragment(){
    }

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.chat_fragment,
                container, false);

        return view;
    }
}
