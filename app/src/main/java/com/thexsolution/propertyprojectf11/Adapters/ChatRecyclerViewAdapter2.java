package com.thexsolution.propertyprojectf11.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thexsolution.propertyprojectf11.Model.MessageModel;
import com.thexsolution.propertyprojectf11.R;

import java.util.List;

/**
 * Created by ArslanNasr on 5/19/2019.
 */

public class ChatRecyclerViewAdapter2 extends RecyclerView.Adapter<ChatRecyclerViewAdapter2.SentMessageHolder> {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private Context context;
    private List<MessageModel> data;
    private LayoutInflater inflater;
    private String userId;


    public ChatRecyclerViewAdapter2(Context context, List<MessageModel> data, String userId) {
        this.context = context;
        this.data = data;
        this.userId = userId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        Log.d("UserID", "getItemCount");
        return data.size();
    }


    @NonNull
    @Override
    public ChatRecyclerViewAdapter2.SentMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout, parent, false);
        SentMessageHolder holder = new SentMessageHolder(view);
        Log.d("mmmmm","view");
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ChatRecyclerViewAdapter2.SentMessageHolder holder, int position) {
        MessageModel message = data.get(position);
        Log.d("mmmmm","bind");
        if (message.getUserId().equals(userId)) {
            holder.left.setVisibility(View.GONE);
            holder.right.setVisibility(View.VISIBLE);
        } else {
            holder.right.setVisibility(View.GONE);
            holder.left.setVisibility(View.VISIBLE);
        }


    }


    public class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView name, message_body, nameLeft, messBodyLeft;
        RelativeLayout left,right;

        SentMessageHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            message_body = itemView.findViewById(R.id.message_body);
            nameLeft = itemView.findViewById(R.id.name_left);
            messBodyLeft = itemView.findViewById(R.id.message_body_left);
            left=itemView.findViewById(R.id.layout_left);
            right=itemView.findViewById(R.id.layout_right);
        }


    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView name, messageText;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            messageText = itemView.findViewById(R.id.message_body);
        }

        void bind(final MessageModel message, int position) {
            name.setText(message.getName());
            messageText.setText(message.getText());
        }
    }

}
