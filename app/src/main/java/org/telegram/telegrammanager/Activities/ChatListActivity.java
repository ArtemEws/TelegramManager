package org.telegram.telegrammanager.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import org.telegram.telegrammanager.R;

public class ChatListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

//        RecyclerView rv = (RecyclerView) findViewById(R.id.chats_rv);
//        rv.setHasFixedSize(true);
//
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        rv.setLayoutManager(llm);
//
//        List groups = new ArrayList<ChatCard>();
//        groups.add(new ChatCard("ArtemLebedev", 24856823, R.drawable.logo));
//        groups.add(new ChatCard("Mr.Freeman", 27434324, R.drawable.logo));
//        groups.add(new ChatCard("[netstalkers]", 28424524, R.drawable.logo));
//
//        RVAdapter adapter = new RVAdapter(groups);
//        rv.setAdapter(adapter);
    }
}
