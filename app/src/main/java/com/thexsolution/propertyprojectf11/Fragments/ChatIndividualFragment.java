package com.thexsolution.propertyprojectf11.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thexsolution.propertyprojectf11.Activities.ConversationActivity;
import com.thexsolution.propertyprojectf11.Activities.SaleDataViewActivity;
import com.thexsolution.propertyprojectf11.Adapters.ChatListRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Adapters.SaleRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Model.ChatList;
import com.thexsolution.propertyprojectf11.Model.Survey;
import com.thexsolution.propertyprojectf11.R;
import com.thexsolution.propertyprojectf11.Util.ConnectionDetect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatIndividualFragment extends Fragment {

    private DatabaseReference mDatabaseReference;
    private RecyclerView rentRecyclerViewID;
    List<ChatList> chatLists;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    FirebaseDatabase mDatabase;
    private ConnectionDetect connectionDetect;
    ProgressDialog progressDialog;
    ChatListRecyclerViewAdapter surveyRecyclerViewAdapter;
    public static final String ACTION_USER_NAME = "action_user_name";
    public static final String ACTION_USER_ID = "action_user_id";
    public static final String ACTION_USER_TOKEN = "action_user_token";


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_individual, container, false);
        connectionDetect = new ConnectionDetect(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(getActivity());
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("PropertyUsers");
        mDatabaseReference.keepSynced(true);
        chatLists = new ArrayList<>();
        rentRecyclerViewID = (RecyclerView) view.findViewById(R.id.individulaChatRecyclerView);
        rentRecyclerViewID.setHasFixedSize(true);
        rentRecyclerViewID.setLayoutManager(new LinearLayoutManager(getActivity()));
        rentRecyclerViewID.addOnItemTouchListener(new ChatListRecyclerViewAdapter(getActivity(), rentRecyclerViewID, new ChatListRecyclerViewAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ChatList chatList = chatLists.get(position);
                Intent intent = new Intent(getActivity(), ConversationActivity.class);
                intent.putExtra(ACTION_USER_NAME, chatList.getName());
                intent.putExtra(ACTION_USER_ID, chatList.getUserid());
                intent.putExtra(ACTION_USER_TOKEN, chatList.getUsertoken());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        if (connectionDetect.isConnected()) {

            progressDialog.setMessage("Loading Data Please wait...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mDatabaseReference.orderByChild("date").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        chatLists.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ChatList chat = snapshot.getValue(ChatList.class);
                            if (!chat.getUserid().equals(mUser.getUid())) {
                                chatLists.add(chat);
                                Collections.reverse(chatLists);
                                surveyRecyclerViewAdapter = new ChatListRecyclerViewAdapter(getActivity(), chatLists);
                                rentRecyclerViewID.setAdapter(surveyRecyclerViewAdapter);
                                surveyRecyclerViewAdapter.notifyDataSetChanged();

                                progressDialog.dismiss();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            Toast.makeText(getActivity(), "Turn on your Internet Connection!", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

}
