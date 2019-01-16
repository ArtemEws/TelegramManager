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
        CardView cv;
        TextView groupName;
        TextView groupSubScore;
        ImageView groupImage;

        ChatCardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.group_card);
            groupName = (TextView)itemView.findViewById(R.id.group_name);
            groupSubScore = (TextView)itemView.findViewById(R.id.group_subs_score);
            groupImage = (ImageView)itemView.findViewById(R.id.group_image);
        }
    }

    List<ChatCard> chatList;
    public RVAdapter(List<ChatCard> chatList){
        this.chatList = chatList;
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public ChatCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_card, viewGroup, false);
        ChatCardViewHolder pvh = new ChatCardViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ChatCardViewHolder personViewHolder, int i) {
        personViewHolder.groupName.setText(chatList.get(i).name);
        personViewHolder.groupSubScore.setText(chatList.get(i).subs);
        personViewHolder.groupImage.setImageResource(chatList.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

