package org.telegram.telegrammanager.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.drinkless.td.libcore.telegram.apihelper.Chat;
import org.telegram.telegrammanager.Helpers.ChatListAdapter;
import org.telegram.telegrammanager.Models.ChatCard;
import org.telegram.telegrammanager.R;

import java.util.ArrayList;

import static org.drinkless.td.libcore.telegram.apihelper.Chat.SUPER_GROUP;
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
        tClient.getChats((type, obj) -> {
            if (type == "chats") {
                ArrayList<Chat> chats = (ArrayList<Chat>)obj;

                for(Chat chat : chats){
                    if(chat.isSuperGroup() && chat.isSuperGroupAdmin()) {
                        groups.add(new ChatCard(chat.getTitle(), 228, R.drawable.logo));

                    }
                }
                ChatListAdapter adapter = new ChatListAdapter(groups);
                rv.setAdapter(adapter);
            } else if (type == "ERROR") {

            }
        });

        return view;
        }
}
