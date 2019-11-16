package com.thexsolution.propertyprojectf11.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
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
import com.thexsolution.propertyprojectf11.Adapters.FilterSaleRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Adapters.FilterSaleReminderRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Model.Reminder;
import com.thexsolution.propertyprojectf11.Model.Survey;
import com.thexsolution.propertyprojectf11.R;
import com.thexsolution.propertyprojectf11.Util.ConnectionDetect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.thexsolution.propertyprojectf11.Activities.NavigationDrawerActivity.ACTION_INTEGER_VALUE;
import static com.thexsolution.propertyprojectf11.Activities.NavigationDrawerActivity.ACTION_SPINNER_AMOUNT_VALUE;

public class SaleReminderSurveyRecyclerViewActivity extends AppCompatActivity {
    List<Reminder> reminderList;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    FirebaseDatabase mDatabase;
    private SearchView searchView;
    DatabaseReference mDatabaseReferrence,mDatabaseReference;
    private ProgressDialog mProgressDialog,progressDialog;
    private RecyclerView filterListRecyclerViewID;
    int value = 0;
    FilterSaleReminderRecyclerViewAdapter surveyRentRecyclerViewAdapter;
    List<Reminder> surveyList;
    public static SQLiteDatabase db;
    public static List<String> your_array_list_rent_survey_10PLUS_10MINUS_sale_REMINDER;
    private ConnectionDetect connectionDetect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_reminder_survey_recycler_view);
        connectionDetect = new ConnectionDetect(SaleReminderSurveyRecyclerViewActivity.this);
        Intent intent =getIntent();
        final int value =intent.getIntExtra(ACTION_INTEGER_VALUE,0);
        final String amount =intent.getStringExtra(ACTION_SPINNER_AMOUNT_VALUE);
        getSupportActionBar().setTitle(value+"\t"+"\t"+amount+"");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        db = openOrCreateDatabase("Favourites", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS TableFavourites(ID INTEGER PRIMARY KEY AUTOINCREMENT,image VARCHAR);");
        saveFavouritesImaged();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("SaleReminder").child(mUser.getUid());
        mDatabaseReference.keepSynced(true);
        surveyList = new ArrayList<>();
        filterListRecyclerViewID = (RecyclerView) findViewById(R.id.filterListRecyclerViewID);
        filterListRecyclerViewID.setHasFixedSize(true);
        filterListRecyclerViewID.setLayoutManager(new LinearLayoutManager(SaleReminderSurveyRecyclerViewActivity.this));

        if (connectionDetect.isConnected()) {

            progressDialog.setMessage("Loading Data Please wait...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        mDatabaseReference.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Reminder survey = dataSnapshot.getValue(Reminder.class);
                                surveyList.add(survey);
                                Collections.reverse(surveyList);
                                surveyRentRecyclerViewAdapter = new FilterSaleReminderRecyclerViewAdapter(SaleReminderSurveyRecyclerViewActivity.this, surveyList, value, amount);
                                filterListRecyclerViewID.setAdapter(surveyRentRecyclerViewAdapter);
                                surveyRentRecyclerViewAdapter.notifyDataSetChanged();

                                progressDialog.dismiss();
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

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(SaleReminderSurveyRecyclerViewActivity.this, "Data not Available!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else {
            Toast.makeText(this, "Turn on your Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFavouritesImaged() {

        try {
            your_array_list_rent_survey_10PLUS_10MINUS_sale_REMINDER = new ArrayList<String>();


            Cursor c = db.rawQuery("SELECT * FROM TableFavourites", null);
            if (c.getCount() != 0) {
                if (c.moveToFirst()) {
                    do {
                        your_array_list_rent_survey_10PLUS_10MINUS_sale_REMINDER.add(c.getString(c.getColumnIndex("image")));
                    } while (c.moveToNext());
                }

            } else {
                Toast.makeText(getApplicationContext(), "No data founded", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            String s = e.toString();
        }
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
        SaleReminderSurveyRecyclerViewActivity.this.finish();
    }
}
