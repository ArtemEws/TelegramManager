package org.telegram.telegrammanager.Helpers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.telegrammanager.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ChatCardViewHolder>{
    public static class ChatCardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView chatName;
        TextView chatSubScore;
        ImageView personPhoto;

        ChatCardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            chatName = (TextView)itemView.findViewById(R.id.chat_name);
            chatSubScore = (TextView)itemView.findViewById(R.id.chat_subs_score);
            personPhoto = (ImageView)itemView.findViewById(R.id.chat_photo);
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ChatCardViewHolder pvh = new ChatCardViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ChatCardViewHolder personViewHolder, int i) {
        personViewHolder.chatName.setText(chatList.get(i).name);
        personViewHolder.chatSubScore.setText(chatList.get(i).subs);
        personViewHolder.personPhoto.setImageResource(chatList.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

