package com.thexsolution.propertyprojectf11.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thexsolution.propertyprojectf11.Adapters.FilterReminderRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Adapters.RentReminderRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Model.Reminder;
import com.thexsolution.propertyprojectf11.Model.Survey;
import com.thexsolution.propertyprojectf11.R;
import com.thexsolution.propertyprojectf11.Util.ConnectionDetect;
import com.thexsolution.propertyprojectf11.Util.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.thexsolution.propertyprojectf11.Activities.SurveyRentActivity.ACTION_RENT_LENGTH;
import static com.thexsolution.propertyprojectf11.Activities.SurveyRentActivity.ACTION_RENT_VALUE;

public class FilterListActivity extends AppCompatActivity {
    List<Reminder> reminderList;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    FirebaseDatabase mDatabase;
    private SearchView searchView;
    DatabaseReference mDatabaseReferrence,mDatabaseReference;
    private ProgressDialog mProgressDialog,progressDialog;
    private RecyclerView rentReminderRecyclerViewID;
    String rentValue  = null;
    String rentlength  = null;
    int length = 0;
    int value = 0;
    private FilterReminderRecyclerViewAdapter rentReminderRecyclerViewAdapter;
    String parts[];
    private ConnectionDetect connectionDetect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_list);
        connectionDetect = new ConnectionDetect(FilterListActivity.this);
        Intent intent = getIntent();
        rentValue = intent.getStringExtra(ACTION_RENT_VALUE);
        rentlength = intent.getStringExtra(ACTION_RENT_LENGTH);
        value = Integer.parseInt(rentValue);
        getSupportActionBar().setTitle("Rent"+"\t"+rentValue);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("RentReminder").child(mUser.getUid());
        mDatabaseReference.keepSynced(true);
        reminderList = new ArrayList<>();
        rentReminderRecyclerViewID = (RecyclerView) findViewById(R.id.filterListRecyclerViewID);
        rentReminderRecyclerViewID.setHasFixedSize(true);
        rentReminderRecyclerViewID.setLayoutManager(new LinearLayoutManager(FilterListActivity.this));

        if (connectionDetect.isConnected()){

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){

                        mDatabaseReference.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Reminder reminder = dataSnapshot.getValue(Reminder.class);
                                reminderList.add(reminder);
                                Collections.reverse(reminderList);
                                rentReminderRecyclerViewAdapter = new FilterReminderRecyclerViewAdapter(FilterListActivity.this, reminderList,value);
                                rentReminderRecyclerViewID.setAdapter(rentReminderRecyclerViewAdapter);
                                rentReminderRecyclerViewAdapter.notifyDataSetChanged();
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
                        });
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(FilterListActivity.this, "Data Not Available!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else {
            Toast.makeText(this, "Turn on your Internet Connection!", Toast.LENGTH_SHORT).show();

        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.filterlistmenusearch,menu);
        final MenuItem myactionmenuItem = menu.findItem(R.id.mypartssearch);
        searchView = (SearchView) myactionmenuItem.getActionView();

        //onChangeSearchViewTextColor(searchView);
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                myactionmenuItem.collapseActionView();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<Reminder> filteredmodelist = filter(reminderList,newText);
                rentReminderRecyclerViewAdapter.setfilter(filteredmodelist);
                return true;
            }
        });

        return true;
    }



    private List<Reminder> filter(List<Reminder> p1,String query){
        query = query.toLowerCase();
        final List<Reminder> filteredList = new ArrayList<>();
        for(Reminder model:p1){
            final String text = model.getRentID().toLowerCase() + model.getPerson_nameID().toLowerCase() + model.getOwner_pd_numberID() + model.getMobileSecondNumberID();
            if(text.contains(query)){
                filteredList.add(model);
            }
        }

        return filteredList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            //startActivity(new Intent(RentDataViewActivity.this, DashbaordActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(this, "Please Exit from top!", Toast.LENGTH_SHORT).show();
        FilterListActivity.this.finish();
    }
}
