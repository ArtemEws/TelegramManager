package org.telegram.telegrammanager.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.drinkless.td.libcore.telegram.apihelper.Chat;
import org.telegram.telegrammanager.Helpers.ChatListAdapter;
import org.telegram.telegrammanager.Models.ChatCard;
import org.telegram.telegrammanager.R;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class ChatListFragment extends android.support.v4.app.Fragment {

    private Context context;
    public static String TAG = ChatListFragment.class.getSimpleName();

    public ChatListFragment(){
    }

    public static ChatListFragment newInstance() {
        return new ChatListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.chat_list_fragment,
                container, false);

        context = view.getContext();

        RecyclerView rv = view.findViewById(R.id.chats_rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);

        ArrayList<ChatCard> groups = new ArrayList<ChatCard>();

        AtomicBoolean done = new AtomicBoolean(false);

        int images[] = {R.drawable.logo2, R.drawable.logo1, R.drawable.logo2, R.drawable.logo3};

        tClient.getChats((type, obj) -> {
            if (type == "chats") {
                ArrayList<Chat> chats = (ArrayList<Chat>)obj;
                ArrayList<Chat> myChannels = new ArrayList<>();
                int j = 0;
                for(Chat chat : chats) {
                    if (chat.isChannel() && chat.isSuperGroupAdmin()) {
                        if (chat.getTitle() != null && chat.getLastMessage() != null && chat.getLastMessage().getMessageContent().isText())
                            groups.add(new ChatCard(chat, chat.getTitle(), chat.getLastMessage().getMessageContent().getText(), R.drawable.logo));
                        else
                            groups.add(new ChatCard(chat, chat.getTitle(), "Сообщение", images[j]));

                        myChannels.add(chat);
                        j++;
                    }
                }
                ChatListAdapter adapter = new ChatListAdapter(context, groups);
                adapter.setOnClick((i)->{
                    Toast.makeText(context, myChannels.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                    ChatFragment cf = new ChatFragment();
                    cf.chat = myChannels.get(i);
                    FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                    fragmentManager.replace(R.id.main_fragment_container, cf);
                    fragmentManager.addToBackStack(ChatListFragment.TAG);
                    fragmentManager.commit();
                });
                rv.setAdapter(adapter);
                done.set(true);
                
            } else if (type == "ERROR") {
                Log.e("Get Chat", type);
            }
        });

        while (!done.get()) {}

        return view;
        }

        @Override
        public void onResume(){
            super.onResume();
            FloatingActionButton fab = getActivity().findViewById(R.id.fab);
            fab.show();
            TextView header_text = getActivity().findViewById(R.id.header_text);
            header_text.setText(R.string.app_name);
        }
}
