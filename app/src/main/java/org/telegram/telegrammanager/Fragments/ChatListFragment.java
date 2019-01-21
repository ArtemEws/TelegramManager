package org.telegram.telegrammanager.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class ChatListFragment extends Fragment {

    private Context context;
    private Integer counter;
    private android.support.v4.app.FragmentTransaction mainFragTrans;

    @NonNull
    ChatFragment chatFrag = new ChatFragment();

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

        ChatListAdapter adapter = new ChatListAdapter(context, groups);

        rv.setAdapter(adapter);

        counter = 0;

        tClient.getChats((type, obj) -> {
            if (type == "chats") {
                ArrayList<Chat> chats = (ArrayList<Chat>)obj;
                ArrayList<Chat> channels = (ArrayList<Chat>)obj;
                for(Chat chat : chats){
                    if(chat.isSuperGroup() && chat.isSuperGroupAdmin()) {
                        counter++;
                        groups.add(new ChatCard(chat.getTitle(), 228, R.drawable.logo));
                        adapter.notifyItemInserted(counter);
                        channels.add(chat);
                    }
                }
                adapter.setOnClick((i)->{
                    Toast.makeText(context, "Oke", Toast.LENGTH_SHORT).show();
                    chatFrag.chat = channels;
                    mainFragTrans = getFragmentManager().beginTransaction();
                    mainFragTrans.replace(R.id.frgmnt, chatFrag);
                    mainFragTrans.commit();
                });
            } else if (type == "ERROR") {
                Log.e("Get Chat", type);
            }
        });

        return view;
        }
}
