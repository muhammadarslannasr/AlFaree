package com.thexsolution.propertyprojectf11.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thexsolution.propertyprojectf11.Adapters.ChatRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Adapters.ChatRecyclerViewAdapter2;
import com.thexsolution.propertyprojectf11.Model.MessageModel;
import com.thexsolution.propertyprojectf11.R;
import com.thexsolution.propertyprojectf11.Util.ConnectionDetect;
import com.thexsolution.propertyprojectf11.Util.KeyboardUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.thexsolution.propertyprojectf11.Activities.NavigationDrawerActivity.KEY_NAME;
import static com.thexsolution.propertyprojectf11.Fragments.ChatIndividualFragment.ACTION_USER_ID;
import static com.thexsolution.propertyprojectf11.Fragments.ChatIndividualFragment.ACTION_USER_NAME;
import static com.thexsolution.propertyprojectf11.Fragments.ChatIndividualFragment.ACTION_USER_TOKEN;
import static com.thexsolution.propertyprojectf11.Fragments.HomeFragment.DEFAULT_MSG_LENGTH_LIMIT;

public class ConversationActivity extends AppCompatActivity {
    String userChatName = "";
    String userID = "";
    String userTokenID = "";
    String channelId = "n";
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private List<MessageModel> data;
    private RecyclerView messages_view;
    private EditText editText;
    //private ImageButton send_messageID;
    private CardView send_messageID;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mDatabaseReferenceUserDate;
    private ChatRecyclerViewAdapter chatRecyclerViewAdapter;
    ConnectionDetect connectionDetect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        connectionDetect = new ConnectionDetect(ConversationActivity.this);
        castingWindgets();
        Intent intent = getIntent();
        data = new ArrayList<>();
        userChatName = intent.getStringExtra(ACTION_USER_NAME);
        userID = intent.getStringExtra(ACTION_USER_ID);
        userTokenID = intent.getStringExtra(ACTION_USER_TOKEN);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("Conversations");
        mDatabaseReferenceUserDate = mDatabase.getReference().child("PropertyUsers");
        getSupportActionBar().setTitle(userChatName);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        KeyboardUtils.addKeyboardToggleListener(this, new KeyboardUtils.SoftKeyboardToggleListener()
        {
            @Override
            public void onToggleSoftKeyboard(boolean isVisible)
            {
                Log.d("keyboard", "keyboard visible: "+isVisible);
                messages_view.scrollToPosition(data.size() - 1);
            }
        });

        messages_view = findViewById(R.id.messages_view);
        chatRecyclerViewAdapter = new ChatRecyclerViewAdapter(ConversationActivity.this, data, mUser.getUid());
        messages_view.setLayoutManager(new LinearLayoutManager(this));
        messages_view.setAdapter(chatRecyclerViewAdapter);

        send_messageID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mDatabaseReference.push().getKey();
                if (channelId.equals("n")) {
                    MessageModel model = new MessageModel(id, mUser.getUid(), NavigationDrawerActivity.getPreference(ConversationActivity.this,KEY_NAME), editText.getText().toString(), "");
                    mDatabaseReference.child(userID + "_" + mUser.getUid()).child(id).setValue(model);
                    mDatabaseReferenceUserDate.child(userID).child("date").setValue(new Date().getTime());
                    mDatabaseReferenceUserDate.child(mAuth.getCurrentUser().getUid()).child("date").setValue(new Date().getTime());
                } else {
                    MessageModel model = new MessageModel(id, mUser.getUid(), NavigationDrawerActivity.getPreference(ConversationActivity.this,KEY_NAME), editText.getText().toString(), "");
                    mDatabaseReference.child(channelId).child(id).setValue(model);
                    mDatabaseReferenceUserDate.child(userID).child("date").setValue(new Date().getTime());
                    mDatabaseReferenceUserDate.child(mAuth.getCurrentUser().getUid()).child("date").setValue(new Date().getTime());
                }
                messages_view.scrollToPosition(data.size() - 1);
                editText.setText("");
            }
        });
        if (connectionDetect.isConnected()){
            getData();
        }else {
            Toast.makeText(this, "Turn on your Internet Connection!", Toast.LENGTH_SHORT).show();
        }

    }

    private void castingWindgets() {
        editText = findViewById(R.id.editText);
        send_messageID = findViewById(R.id.send_messageID);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        ConversationActivity.this.finish();
    }

    private void getData() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    if (snapshot.getKey().contains(userID + "_" + mUser.getUid()) || snapshot.getKey().contains(mUser.getUid() + "_" + userID)) {
                        channelId = snapshot.getKey();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            Log.d("ccccccc", "loop");
                            MessageModel model = snapshot1.getValue(MessageModel.class);
                            data.add(model);
                            chatRecyclerViewAdapter.notifyDataSetChanged();
                            messages_view.scrollToPosition(data.size() - 1);
                        }
                    } else {
                        //Toast.makeText(ConversationActivity.this, dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
