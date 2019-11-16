package com.thexsolution.propertyprojectf11.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thexsolution.propertyprojectf11.Model.ChatList;
import com.thexsolution.propertyprojectf11.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ArslanNasr on 7/6/2018.
 */

public class ChatListRecyclerViewAdapter extends RecyclerView.Adapter<ChatListRecyclerViewAdapter.ViewHolder> implements RecyclerView.OnItemTouchListener {

    private LayoutInflater inflater;
    private Context context;
    public TextView contact_nameID, contact_text, time_text;
    public CircleImageView list_image;
    public CheckBox checkboxID;
    public RelativeLayout layoutID;
    public TextView readSms_counterID;
    private ClickListener clickListener;
    private GestureDetector gestureDetector;
    private List<ChatList> smsList;

    public ChatListRecyclerViewAdapter(Context context, List<ChatList> smsList) {
        this.context = context;
        this.smsList = smsList;
    }

    public ChatListRecyclerViewAdapter(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @NonNull
    @Override
    public ChatListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_row, parent, false);
        return new ChatListRecyclerViewAdapter.ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatListRecyclerViewAdapter.ViewHolder holder, final int position) {

        ChatList chatList = smsList.get(position);
        contact_nameID.setText(chatList.getName());
    }

    @Override
    public int getItemCount() {
        return smsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ViewHolder(final View itemView, final Context ctx) {
            super(itemView);
            this.setIsRecyclable(false);
            context = ctx;
            contact_nameID = itemView.findViewById(R.id.contact_nameID);
            contact_text = itemView.findViewById(R.id.contact_text);
            readSms_counterID = itemView.findViewById(R.id.readSms_counterID);
            time_text = itemView.findViewById(R.id.time_text);
            list_image = itemView.findViewById(R.id.list_image);
            //checkboxID = itemView.findViewById(R.id.checkboxID);

        }

        @Override
        public void onClick(View view) {
            //dashboardMessageActivity.prepareSelection(view, getAdapterPosition());
        }
    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


}

