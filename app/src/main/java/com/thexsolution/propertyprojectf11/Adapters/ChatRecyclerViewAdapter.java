package com.thexsolution.propertyprojectf11.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.thexsolution.propertyprojectf11.Model.MessageModel;
import com.thexsolution.propertyprojectf11.R;

import java.util.ArrayList;
import java.util.List;

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private Context context;
    private List<MessageModel> data;
    private LayoutInflater inflater;
    private String userId;


    public ChatRecyclerViewAdapter(Context context, List<MessageModel> data,String userId) {
        this.context = context;
        this.data = data;
        this.userId = userId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        Log.d("UserID","getItemCount");
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
       // MessageModel message = (MessageModel) data.get(position);
        Log.d("UserID","getItemViewType");
        if (data.get(position).getUserId().equals(userId)) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Log.d("UserID","view");
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = inflater
                    .inflate(R.layout.item_message_practice_right, parent, false);
            return new SentMessageHolder(view);
        } else {
            view =inflater.inflate(R.layout.item_message_practice, parent, false);
            return new ReceivedMessageHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.getItemViewType();
        MessageModel message =  data.get(position);
        if(message.getUserId().equals(userId)){
            //show right
        }else {
            //show left
        }
        Log.d("UserID","onBindViewHolder");
        //   imagesArray[position] = message.getImageKey();
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message, position);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message, position);
                break;
        }

    }


    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView name,message_body;
        SentMessageHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            message_body=itemView.findViewById(R.id.message_body);
        }

        void bind(final MessageModel message, int position) {
            name.setText(message.getName());
            message_body.setText(message.getText());
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView name,messageText;
        ReceivedMessageHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            messageText=itemView.findViewById(R.id.message_body);
        }

        void bind(final MessageModel message, int position) {
            name.setText(message.getName());
            messageText.setText(message.getText());
        }
    }

}
