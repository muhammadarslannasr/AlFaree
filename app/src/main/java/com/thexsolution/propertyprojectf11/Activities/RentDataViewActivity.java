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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Model.Survey;
import com.thexsolution.propertyprojectf11.R;
import com.thexsolution.propertyprojectf11.Util.ConnectionDetect;
import com.thexsolution.propertyprojectf11.Util.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RentDataViewActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private RecyclerView rentRecyclerViewID;
    List<Survey> surveyList;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private SearchView searchView;
    FirebaseDatabase mDatabase;
    private ConnectionDetect connectionDetect;
    ProgressDialog progressDialog;
    SurveyRecyclerViewAdapter surveyRecyclerViewAdapter;
    public static SQLiteDatabase db;
    public static List<String> your_array_list;
    private LinearLayout layout_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_data_view);
        connectionDetect = new ConnectionDetect(RentDataViewActivity.this);
        getSupportActionBar().setTitle(Utility.getStringFromRes(RentDataViewActivity.this, R.string.survey_data));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        db = openOrCreateDatabase("Favourites", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS TableFavourites(ID INTEGER PRIMARY KEY AUTOINCREMENT,image VARCHAR);");
        saveFavouritesImaged();
        //layout_empty = (LinearLayout) findViewById(R.id.layout_empty);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("SurveyData").child(mUser.getUid());
        mDatabaseReference.keepSynced(true);
        surveyList = new ArrayList<>();
        rentRecyclerViewID = (RecyclerView) findViewById(R.id.rentRecyclerViewID);
        rentRecyclerViewID.setHasFixedSize(true);
        rentRecyclerViewID.setLayoutManager(new LinearLayoutManager(RentDataViewActivity.this));

//        if (connectionDetect.isConnected()) {
//
//            progressDialog.setMessage("Loading Data Please wait...");
//            progressDialog.setCanceledOnTouchOutside(false);
//            progressDialog.show();
//
//
//            mDatabaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()) {
//
//                        mDatabaseReference.addChildEventListener(new ChildEventListener() {
//                            @Override
//                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                                Survey survey = dataSnapshot.getValue(Survey.class);
//                                surveyList.add(survey);
//                                Collections.reverse(surveyList);
//                                surveyRecyclerViewAdapter = new SurveyRecyclerViewAdapter(RentDataViewActivity.this, surveyList);
//                                rentRecyclerViewID.setAdapter(surveyRecyclerViewAdapter);
//                                surveyRecyclerViewAdapter.notifyDataSetChanged();
//
//                                progressDialog.dismiss();
//                            }
//
//                            @Override
//                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                            }
//
//                            @Override
//                            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                            }
//
//                            @Override
//                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                            }
//                        });
//
//                    } else {
//                        progressDialog.dismiss();
//                        Toast.makeText(RentDataViewActivity.this, "Data Not Exists!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//
//        }else {
//            Toast.makeText(this, "Turn on your Internet Connection!", Toast.LENGTH_SHORT).show();
//        }

        if (connectionDetect.isConnected()) {

//            progressDialog.setMessage("Loading Data Please wait...");
//            progressDialog.setCanceledOnTouchOutside(false);
//            progressDialog.show();

            mDatabaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Survey survey = dataSnapshot.getValue(Survey.class);
                    surveyList.add(survey);
                    Collections.reverse(surveyList);
                    surveyRecyclerViewAdapter = new SurveyRecyclerViewAdapter(RentDataViewActivity.this, surveyList);
                    rentRecyclerViewID.setAdapter(surveyRecyclerViewAdapter);
                    surveyRecyclerViewAdapter.notifyDataSetChanged();

                    //progressDialog.dismiss();
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
            //progressDialog.dismiss();
            Toast.makeText(RentDataViewActivity.this, "Turn on your Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFavouritesImaged() {

        try {
            your_array_list = new ArrayList<String>();


            Cursor c = db.rawQuery("SELECT * FROM TableFavourites", null);
            if (c.getCount() != 0) {
                if (c.moveToFirst()) {
                    do {
                        your_array_list.add(c.getString(c.getColumnIndex("image")));
                    } while (c.moveToNext());
                }

            } else {
                //Toast.makeText(getApplicationContext(), "No data founded", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            String s = e.toString();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.partsmenusearch,menu);
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
                final List<Survey> filteredmodelist = filter(surveyList,newText);
                surveyRecyclerViewAdapter.setfilter(filteredmodelist);
                return true;
            }
        });

        return true;
    }



    private List<Survey> filter(List<Survey> p1,String query){
        query = query.toLowerCase();
        final List<Survey> filteredList = new ArrayList<>();
        for(Survey model:p1){
            final String text = model.getSector_nameID().toLowerCase() + model.getRentID().toLowerCase() + model.getOwner_pd_numberID() + model.getMobileSecondNumberID();
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
        RentDataViewActivity.this.finish();
    }
}
