package com.thexsolution.propertyprojectf11.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.thexsolution.propertyprojectf11.Adapters.GroupChatRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Adapters.MessageAdapter;
import com.thexsolution.propertyprojectf11.Model.FriendlyMessage;
import com.thexsolution.propertyprojectf11.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.thexsolution.propertyprojectf11.Activities.NavigationDrawerActivity.myUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private EditText mMessageEditText;
    private CardView mSendButton;

    private static final String TAG = "ChatActivity";

    public static final String ANONYMOUS = "Anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    private String mUsername;
    ListView mMessageListView;
    public static FirebaseDatabase database;
    public DatabaseReference messageReference;
    private ChildEventListener messageEventListener;
    public static MessageAdapter mMessageAdapter;
    private GroupChatRecyclerViewAdapter groupChatRecyclerViewAdapter;
    List<FriendlyMessage> friendlyMessages;
    Query lastQuery;
    DatabaseReference appRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        database = FirebaseDatabase.getInstance();
        messageReference = FirebaseDatabase.getInstance().getReference().child("GroupMessages");
        messageReference.keepSynced(true);
        //messageReference = appRef.child("messages");
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        messageEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FriendlyMessage new_message =  dataSnapshot.getValue(FriendlyMessage.class);
                mMessageAdapter.add(new_message);
                mMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(getActivity(), R.layout.item_message, friendlyMessages);
        lastQuery = messageReference.orderByKey().limitToLast(20);
        // lastQuery.addChildEventListener(messageEventListener);
        //    messageReference.addChildEventListener(messageEventListener);

        mMessageListView = (ListView)view.findViewById(R.id.messagelistview);
        mMessageEditText = (EditText) view.findViewById(R.id.messageEditText);
        mSendButton = (CardView) view.findViewById(R.id.sendButton);
        mUsername = ANONYMOUS;
        //mUsername = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        mMessageListView.setAdapter(mMessageAdapter);

        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference newPost = messageReference.push();
                String ID = newPost.getKey();
                FriendlyMessage friendlyMessage = new FriendlyMessage(mMessageEditText.getText().toString(), myUser.name,ID,String.valueOf(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())));
                newPost.setValue(friendlyMessage);
                mMessageEditText.setText("");
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        lastQuery.addChildEventListener(messageEventListener);
        mMessageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        lastQuery.removeEventListener(messageEventListener);
        friendlyMessages.clear();
        mMessageAdapter.notifyDataSetChanged();
    }


}
