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
import org.drinkless.td.libcore.telegram.TdApi;
import org.telegram.telegrammanager.Models.MessageCard;
import org.telegram.telegrammanager.R;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageCardViewHolder> {

    public static class MessageCardViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public TextView authorName;
        public TextView messageContent;
        MessageCardViewHolder(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.message_card);
            authorName = itemView.findViewById(R.id.author_name);
            messageContent = itemView.findViewById(R.id.message_content);
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
        View v = inflater.inflate(R.layout.message_layout, viewGroup, false);
        MessageListAdapter.MessageCardViewHolder messageCardVh = new MessageListAdapter.MessageCardViewHolder(v);
        return messageCardVh;
    }

    @Override
    public void onBindViewHolder(MessageListAdapter.MessageCardViewHolder messageCardViewHolder, int i) {
        messageCardViewHolder.authorName.setText(messageList.get(i).author);
        messageCardViewHolder.messageContent.setText(messageList.get(i).message);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
