package org.telegram.telegrammanager.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.drinkless.td.libcore.telegram.TdApi;
import org.drinkless.td.libcore.telegram.apihelper.Chat;
import org.telegram.telegrammanager.R;

public class ChatFragment extends Fragment {

    private static Context context;
    public Chat chat;
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

        context = view.getContext();
        return view;
    }
}
