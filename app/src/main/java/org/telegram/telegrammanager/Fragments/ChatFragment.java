package org.telegram.telegrammanager.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.drinkless.td.libcore.telegram.apihelper.Chat;
import org.drinkless.td.libcore.telegram.apihelper.Message;
import org.telegram.telegrammanager.Helpers.MessageListAdapter;
import org.telegram.telegrammanager.Models.MessageCard;
import org.telegram.telegrammanager.R;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

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

        TextView header_text = getActivity().findViewById(R.id.header_text);
        header_text.setText(chat.getTitle());


        RecyclerView rv = view.findViewById(R.id.messages_rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);

        llm.setReverseLayout(true);
        rv.setLayoutManager(llm);

        ArrayList<MessageCard> messageCards = new ArrayList<MessageCard>();

        AtomicBoolean done = new AtomicBoolean(false);

        tClient.getChatMessages(chat, (type, obj) -> {
            ArrayList<Message> messages = (ArrayList<Message>) obj;

            for(Message message : messages){
                if(message.getMessageContent().isText()){
                    messageCards.add(new MessageCard(chat.getTitle(), message.getMessageContent().getText()));
                }
            }
            MessageListAdapter adapter = new MessageListAdapter(messageCards);
            rv.setAdapter(adapter);
            done.set(true);
        });

        while (!done.get()) {}

        return view;
    }

    public void onResume() {
        super.onResume();

        TextView header_text = getActivity().findViewById(R.id.header_text);
        header_text.setText(chat.getTitle());

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.hide();
    }

}
