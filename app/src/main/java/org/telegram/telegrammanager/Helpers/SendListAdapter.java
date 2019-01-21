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
import org.telegram.telegrammanager.Models.ChatCard;
import org.telegram.telegrammanager.R;

import java.util.List;

public class SendListAdapter extends RecyclerView.Adapter<SendListAdapter.GroupCardViewHolder> {

    public static class GroupCardViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public TextView groupName;
        public ImageView groupImage;

        GroupCardViewHolder(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.message_card);
            groupName = itemView.findViewById(R.id.group_name);
            groupImage = itemView.findViewById(R.id.group_image);
        }
    }

    public List<ChatCard> groupList;

    public SendListAdapter(List<ChatCard> groupList){
        this.groupList = groupList;
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    @Override
    public SendListAdapter.GroupCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.group_layout, viewGroup, false);
        SendListAdapter.GroupCardViewHolder groupCardVh = new SendListAdapter.GroupCardViewHolder(v);
        return groupCardVh;
    }

    @Override
    public void onBindViewHolder(SendListAdapter.GroupCardViewHolder messageCardViewHolder, int i) {
        messageCardViewHolder.groupName.setText(groupList.get(i).name);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
