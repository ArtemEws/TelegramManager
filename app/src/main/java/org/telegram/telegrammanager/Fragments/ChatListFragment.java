package org.telegram.telegrammanager.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.drinkless.td.libcore.telegram.apihelper.Chat;
import org.telegram.telegrammanager.Helpers.ChatListAdapter;
import org.telegram.telegrammanager.Models.ChatCard;
import org.telegram.telegrammanager.R;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class ChatListFragment extends Fragment {

    private Context context;

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

        tClient.getChats((type, obj) -> {
            if (type == "chats") {
                ArrayList<Chat> chats = (ArrayList<Chat>)obj;
                ArrayList<Chat> myChannels = new ArrayList<>();
                for(Chat chat : chats){
                    if(chat.isSuperGroup() && chat.isSuperGroupAdmin()) {
                        groups.add(new ChatCard(chat.getTitle(), 228, R.drawable.logo));
                        myChannels.add(chat);
                    }
                }

                ChatListAdapter adapter = new ChatListAdapter(context, groups);
                adapter.setOnClick((i)->{
                    Toast.makeText(context, myChannels.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                    ChatFragment cf = new ChatFragment();
                    cf.chat = myChannels.get(i);
                    FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                    fragmentManager.replace(R.id.frgmnt, cf);
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

    @UiThread
    protected void dataSetChanged(ChatListAdapter adapter) {
        adapter.notifyDataSetChanged();
    }
}
