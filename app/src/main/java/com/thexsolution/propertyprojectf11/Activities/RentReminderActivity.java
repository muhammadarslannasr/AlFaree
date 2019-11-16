package com.thexsolution.propertyprojectf11.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thexsolution.propertyprojectf11.Adapters.RentReminderRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Adapters.SaleReminderRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Model.Reminder;
import com.thexsolution.propertyprojectf11.R;
import com.thexsolution.propertyprojectf11.Util.ConnectionDetect;
import com.thexsolution.propertyprojectf11.Util.Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentReminderActivity extends AppCompatActivity {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private FloatingActionButton fab;
    private EditText person_nameID,owner_pd_numberID,mobileSecondNumberID,rentID,adTitleID,descriptionID;
    private Button submitbuttonId;
    private ImageView adbtnID;
    private Spinner rentorsaleId;
    private String rentListSpinner = null;
    public boolean isClickedFirstTime = true;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReferrence,mDatabaseReference;
    private ProgressDialog mProgressDialog,progressDialog;
    List<Reminder> reminderList;
    private SearchView searchView;
    private RecyclerView rentReminderRecyclerViewID;
    private RentReminderRecyclerViewAdapter rentReminderRecyclerViewAdapter;
    public static SQLiteDatabase db;
    public static List<String> your_array_list_rent_reminder;
    private ConnectionDetect connectionDetect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_reminder);
        connectionDetect = new ConnectionDetect(RentReminderActivity.this);
        getSupportActionBar().setTitle(Utility.getStringFromRes(RentReminderActivity.this,R.string.rent_reminder));
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        db = openOrCreateDatabase("RentReminderFavourites", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS TableFavourites(ID INTEGER PRIMARY KEY AUTOINCREMENT,image VARCHAR);");
        saveFavouritesImaged();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addRentReminder();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("RentReminder").child(mUser.getUid());
        mDatabaseReference.keepSynced(true);
        reminderList = new ArrayList<>();
        rentReminderRecyclerViewID = (RecyclerView) findViewById(R.id.rentReminderRecyclerViewID);
        rentReminderRecyclerViewID.setHasFixedSize(true);
        rentReminderRecyclerViewID.setLayoutManager(new LinearLayoutManager(RentReminderActivity.this));

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
//
//                    if (dataSnapshot.exists()) {
//
//                        mDatabaseReference.addChildEventListener(new ChildEventListener() {
//                            @Override
//                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                                Reminder reminder = dataSnapshot.getValue(Reminder.class);
//                                reminderList.add(reminder);
//                                Collections.reverse(reminderList);
//                                rentReminderRecyclerViewAdapter = new RentReminderRecyclerViewAdapter(RentReminderActivity.this, reminderList);
//                                rentReminderRecyclerViewID.setAdapter(rentReminderRecyclerViewAdapter);
//                                rentReminderRecyclerViewAdapter.notifyDataSetChanged();
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
//                        Toast.makeText(RentReminderActivity.this, "Data Not Available", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }else {
//            Toast.makeText(this, "Turn on your Internet Connection!", Toast.LENGTH_SHORT).show();
//        }

        if (connectionDetect.isConnected()){
            mDatabaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Reminder reminder = dataSnapshot.getValue(Reminder.class);
                    reminderList.add(reminder);
                    Collections.reverse(reminderList);
                    rentReminderRecyclerViewAdapter = new RentReminderRecyclerViewAdapter(RentReminderActivity.this, reminderList);
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
            Toast.makeText(RentReminderActivity.this, "Turn on your Internet Connection!", Toast.LENGTH_SHORT).show();
        }




        }

    private void saveFavouritesImaged() {

        try {
            your_array_list_rent_reminder = new ArrayList<String>();


            Cursor c = db.rawQuery("SELECT * FROM TableFavourites", null);
            if (c.getCount() != 0) {
                if (c.moveToFirst()) {
                    do {
                        your_array_list_rent_reminder.add(c.getString(c.getColumnIndex("image")));
                    } while (c.moveToNext());
                }

            } else {
                //Toast.makeText(getApplicationContext(), "No data founded", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            String s = e.toString();
        }
    }

    private void addRentReminder() {

        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.rent_reminder_dialog_add_layout, null);
        person_nameID = view.findViewById(R.id.person_nameID);
        owner_pd_numberID = view.findViewById(R.id.owner_pd_numberID);
        mobileSecondNumberID = view.findViewById(R.id.mobileSecondNumberID);
        rentID = view.findViewById(R.id.rentID);
        adTitleID = view.findViewById(R.id.adTitleID);
        descriptionID = view.findViewById(R.id.descriptionID);
        adbtnID = view.findViewById(R.id.adbtnID);
        submitbuttonId = view.findViewById(R.id.submitbuttonId);
        rentorsaleId = view.findViewById(R.id.rentorsaleId);

        rentOrSaleSpinner();

        adbtnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change boolean value
                if (isClickedFirstTime) {
                    mobileSecondNumberID.setVisibility(View.VISIBLE);
                    isClickedFirstTime = false;
                } else {
                    mobileSecondNumberID.setVisibility(View.GONE);
                    isClickedFirstTime = true;
                }

            }
        });

        mProgressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabaseReferrence = FirebaseDatabase.getInstance().getReference().child("RentReminder").child(mUser.getUid());

        submitbuttonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mProgressDialog.setMessage("Adding Data....");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();

                DatabaseReference newPost = mDatabaseReferrence.push();
                String ID = newPost.getKey();
                Map<String,String> data = new HashMap<>();
                data.put("person_nameID",person_nameID.getText().toString().trim());
                data.put("owner_pd_numberID",owner_pd_numberID.getText().toString().trim());
                data.put("timestamp",String.valueOf(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())));
                data.put("rentReminder_nodeID",ID);
                data.put("mobileSecondNumberID",mobileSecondNumberID.getText().toString().trim());
                data.put("rentID",rentID.getText().toString().trim());
                data.put("rentorsaleId",rentListSpinner);
                data.put("adTitleID",adTitleID.getText().toString().trim());
                data.put("descriptionID",descriptionID.getText().toString().trim());
                newPost.setValue(data);
                Toast.makeText(RentReminderActivity.this, "Rent Reminder Added!", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
                dialog.dismiss();
            }
        });


        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();
    }

    public void rentOrSaleSpinner(){
        final List<String> rentOrSaleList = new ArrayList<>();

        rentOrSaleList.add("Rent");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,rentOrSaleList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rentorsaleId.setAdapter(arrayAdapter);
        rentorsaleId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                rentListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
            final String text = model.getPerson_nameID().toLowerCase() + model.getRentID().toLowerCase() + model.getOwner_pd_numberID().toLowerCase() + model.getMobileSecondNumberID().toLowerCase();
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
        RentReminderActivity.this.finish();
    }
}
