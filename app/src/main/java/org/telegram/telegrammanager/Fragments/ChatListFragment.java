package org.telegram.telegrammanager.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.telegram.telegrammanager.Helpers.RVAdapter;
import org.telegram.telegrammanager.Models.ChatCard;
import org.telegram.telegrammanager.R;

import java.util.ArrayList;
import java.util.List;

public class ChatListFragment extends Fragment {

    private RecyclerView rv;
    private RecyclerView.Adapter RVAdapter;
    private RecyclerView.LayoutManager llm;
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

        List groups = new ArrayList<ChatCard>();
        groups.add(new ChatCard("ArtemLebedev", 24856823, R.drawable.logo));
        groups.add(new ChatCard("Mr.Freeman", 27434324, R.drawable.logo));
        groups.add(new ChatCard("[netstalkers]", 28424524, R.drawable.logo));

        RVAdapter adapter = new RVAdapter(groups);
        rv.setAdapter(adapter);
        return view;
        }
}
