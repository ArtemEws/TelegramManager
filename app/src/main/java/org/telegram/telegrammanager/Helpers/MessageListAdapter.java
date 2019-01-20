package org.telegram.telegrammanager.Helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.telegrammanager.Models.MessageCard;
import org.telegram.telegrammanager.R;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageCardViewHolder> {

    public static class MessageCardViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public TextView groupName;
        public TextView groupSubScore;
        public ImageView groupImage;

        MessageCardViewHolder(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.message_card);
            groupName = itemView.findViewById(R.id.group_name);
            groupSubScore = itemView.findViewById(R.id.group_subs_score);
            groupImage = itemView.findViewById(R.id.group_image);
        }
    }

    public List<MessageCard> messageList;

    public MessageListAdapter(List<MessageCard> groupList){
        this.messageList = groupList;
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public MessageListAdapter.MessageCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.group_layout, viewGroup, false);
        MessageListAdapter.MessageCardViewHolder messageCardVh = new MessageListAdapter.MessageCardViewHolder(v);
        return messageCardVh;
    }

    @Override
    public void onBindViewHolder(MessageListAdapter.MessageCardViewHolder messageCardViewHolder, int i) {
        messageCardViewHolder.groupName.setText(messageList.get(i).author);
        messageCardViewHolder.groupSubScore.setText(messageList.get(i).message);
//        messageCardViewHolder.groupImage.setImageResource(messageList.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
