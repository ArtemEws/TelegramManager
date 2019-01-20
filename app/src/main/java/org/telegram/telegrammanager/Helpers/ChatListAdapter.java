package org.telegram.telegrammanager.Helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.telegram.telegrammanager.Models.ChatCard;
import org.telegram.telegrammanager.R;

import java.util.List;

import static org.telegram.telegrammanager.Helpers.TGClient.tClient;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatCardViewHolder>{

    private Context context;

    public static class ChatCardViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public TextView groupName;
        public TextView groupSubScore;
        public ImageView groupImage;

        ChatCardViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.group_card);
            groupName = itemView.findViewById(R.id.group_name);
            groupSubScore = itemView.findViewById(R.id.group_subs_score);
            groupImage = itemView.findViewById(R.id.group_image);

        }
    }

    public List<ChatCard> groupList;

    public ChatListAdapter(Context context, List<ChatCard> groupList){
        this.groupList = groupList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    @Override
    public ChatListAdapter.ChatCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.group_layout, viewGroup, false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                Toast.makeText(context, position, Toast.LENGTH_LONG).show();
            }
        });

        ChatCardViewHolder chatCardVh = new ChatCardViewHolder(v);

        return chatCardVh;
    }

    @Override
    public void onBindViewHolder(ChatCardViewHolder chatCardViewHolder, int i) {
        chatCardViewHolder.groupName.setText(groupList.get(i).name);
        chatCardViewHolder.groupSubScore.setText(groupList.get(i).subs.toString());
        chatCardViewHolder.groupImage.setImageResource(groupList.get(i).photoId);
        chatCardViewHolder.itemView.setTag(i);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

