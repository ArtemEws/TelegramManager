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

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatCardViewHolder>{
    private OnItemClicked onClick;
    private Context context;
    //make interface like this
    public interface OnItemClicked {
        void onItemClick(int position);
    }

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
        ChatCardViewHolder chatCardVh = new ChatCardViewHolder(v);
        return chatCardVh;
    }

    @Override
    public void onBindViewHolder(ChatCardViewHolder chatCardViewHolder, int i) {
        chatCardViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(i);
            }
        });
        chatCardViewHolder.groupName.setText(groupList.get(i).name);
        chatCardViewHolder.groupSubScore.setText(groupList.get(i).lastMes);
        chatCardViewHolder.groupImage.setImageResource(groupList.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
}

