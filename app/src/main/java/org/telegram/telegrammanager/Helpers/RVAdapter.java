package org.telegram.telegrammanager.Helpers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.telegrammanager.Models.ChatCard;
import org.telegram.telegrammanager.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ChatCardViewHolder>{

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

    List<ChatCard> groupList;

    public RVAdapter(List<ChatCard> groupList){
        this.groupList = groupList;
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    @Override
    public ChatCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_layout, viewGroup, false);
        ChatCardViewHolder chatCardVh = new ChatCardViewHolder(v);
        return chatCardVh;
    }

    @Override
    public void onBindViewHolder(ChatCardViewHolder chatCardViewHolder, int i) {
        chatCardViewHolder.groupName.setText(groupList.get(i).name);
        chatCardViewHolder.groupSubScore.setText(groupList.get(i).subs);
        chatCardViewHolder.groupImage.setImageResource(groupList.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

