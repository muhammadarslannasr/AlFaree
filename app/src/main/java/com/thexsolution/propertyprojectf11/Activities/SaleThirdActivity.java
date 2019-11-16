package com.thexsolution.propertyprojectf11.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thexsolution.propertyprojectf11.R;
import com.thexsolution.propertyprojectf11.Util.Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thexsolution.propertyprojectf11.Activities.NavigationDrawerActivity.ACTION_INTEGER_VALUE;
import static com.thexsolution.propertyprojectf11.Activities.NavigationDrawerActivity.ACTION_SPINNER_AMOUNT_VALUE;

public class SaleThirdActivity extends AppCompatActivity {
    private Spinner furnishId,areaSpinnerId,floorSpinnerId,rentorsaleId,ownerorpropertyID;
    String furnishListSpinner;
    String areaListSpinner;
    String floorListSpinner;
    String rentorsaleListSpinner;
    String ownerorpropertyListSpinner;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText sector_nameID,street_numberID,house_numberID,owner_pd_numberID,bedroomID,bathID,tvLaunchID,drying_roomID,dinning_roomID;
    private EditText servant_roomID,advanceID,securityID,rentID,areaUnitID,adTitleID,descriptionID,mobileSecondNumberID;
    private CheckBox car_parkingCheckBoxID,seperate_gasCheckBoxId,sep_elecCheckBoxId,motorBorCheckBoxID,fullHouseCheckBoxId,gateSeperateCheckBoxId;
    private Button submitbuttonId;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReferrence;
    private ProgressDialog mProgressDialog;
    String carCheckBoxChecked = "null";
    String seperategasChecked = "null";
    String sepElecChecked = "null";
    String motorBorChecked = "null";
    String fullHouseChecked = "null";
    String gateSeperateChecked = "null";
    private Button nextbuttonId;
    private ImageView adbtnID;
    public boolean isClickedFirstTime = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_third);
        init();
        getSupportActionBar().setTitle(Utility.getStringFromRes(SaleThirdActivity.this,R.string.sale_three));
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mProgressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabaseReferrence = FirebaseDatabase.getInstance().getReference().child("SurveySaleData").child(mUser.getUid());


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

        nextbuttonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        car_parkingCheckBoxID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (car_parkingCheckBoxID.isChecked()){
                    carCheckBoxChecked = "Yes";
                }else {
                    carCheckBoxChecked = "No";
                }
            }
        });

        seperate_gasCheckBoxId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seperate_gasCheckBoxId.isChecked()) {
                    seperategasChecked = "Yes";
                }else {
                    seperategasChecked = "No";
                }
            }
        });

        sep_elecCheckBoxId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sep_elecCheckBoxId.isChecked()){
                    sepElecChecked = "Yes";
                }else {
                    sepElecChecked = "No";
                }
            }
        });

        motorBorCheckBoxID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (motorBorCheckBoxID.isChecked()){
                    motorBorChecked = "Yes";
                }else {
                    motorBorChecked = "No";
                }
            }
        });

        fullHouseCheckBoxId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fullHouseCheckBoxId.isChecked()){
                    fullHouseChecked = "Yes";
                }else {
                    fullHouseChecked = "No";
                }
            }
        });

        gateSeperateCheckBoxId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gateSeperateCheckBoxId.isChecked()){
                    gateSeperateChecked = "Yes";
                }else {
                    gateSeperateChecked = "No";
                }
            }
        });

        furnishSpinner();
        areaSpinner();
        floorSpinner();
        rentOrSaleSpinner();
        OwnerOrPropertySpinner();

        submitbuttonId.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Button Pressed
                    submitbuttonId.setBackgroundColor(getResources().getColor(R.color.button_background_color_after_pressed));
                    addSurveyData();
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    //finger was lifted
                    submitbuttonId.setBackgroundColor(Color.parseColor("#5E9DC0"));
                }
                return false;
            }
        });
    }

    private void addSurveyData() {
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(Utility.getStringFromRes(SaleThirdActivity.this,R.string.sure));

        dialogBuilder.setPositiveButton(Utility.getStringFromRes(SaleThirdActivity.this, R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(SaleThirdActivity.this,SaleReminderSurveyRecyclerViewActivity.class);
                intent.putExtra(ACTION_INTEGER_VALUE,Integer.parseInt(rentID.getText().toString().trim()));
                intent.putExtra(ACTION_SPINNER_AMOUNT_VALUE,rentorsaleListSpinner);
                startActivity(intent);
            }
        });

        dialogBuilder.setNeutralButton("Later!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogBuilder.setNegativeButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mProgressDialog.setMessage("Adding Data....");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();

                DatabaseReference newPost = mDatabaseReferrence.push();
                String ID = newPost.getKey();
                Map<String,String> data = new HashMap<>();
                data.put("sector_nameID",sector_nameID.getText().toString().trim());
                data.put("street_numberID",street_numberID.getText().toString().trim());
                data.put("timestamp",String.valueOf(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())));
                data.put("survey_nodeID",ID);
                data.put("house_numberID",house_numberID.getText().toString().trim());
                data.put("owner_pd_numberID",owner_pd_numberID.getText().toString().trim());
                data.put("bedroomID",bedroomID.getText().toString().trim());
                data.put("bathID",bathID.getText().toString().trim());
                data.put("ownerorpropertyID",ownerorpropertyListSpinner);
                data.put("tvLaunchID",tvLaunchID.getText().toString().trim());
                data.put("drying_roomID",drying_roomID.getText().toString().trim());
                data.put("dinning_roomID",dinning_roomID.getText().toString().trim());
                data.put("servant_roomID",servant_roomID.getText().toString().trim());
                data.put("advanceID",advanceID.getText().toString().trim());
                data.put("securityID",securityID.getText().toString().trim());
                data.put("mobileSecondNumberID",mobileSecondNumberID.getText().toString().trim());
                data.put("rentID",rentID.getText().toString().trim());
                data.put("rentorsaleId",rentorsaleListSpinner);
                data.put("furnishId",furnishListSpinner);
                if (carCheckBoxChecked.equals("null")){
                    data.put("car_parkingCheckBoxID","No");
                }else {
                    data.put("car_parkingCheckBoxID",carCheckBoxChecked);
                }
                if (seperategasChecked.equals("null")){
                    data.put("seperate_gasCheckBoxId","No");
                }else {
                    data.put("seperate_gasCheckBoxId",seperategasChecked);
                }
                if (sepElecChecked.equals("null")){
                    data.put("sep_elecCheckBoxId","No");
                }else {
                    data.put("sep_elecCheckBoxId",sepElecChecked);
                }
                if (motorBorChecked.equals("null")){
                    data.put("motorBorCheckBoxID","No");
                }else {
                    data.put("motorBorCheckBoxID",motorBorChecked);
                }
                if (gateSeperateChecked.equals("null")){
                    data.put("gateSeperateCheckBoxId","No");
                }else {
                    data.put("gateSeperateCheckBoxId",gateSeperateChecked);
                }
                if (fullHouseChecked.equals("null")){
                    data.put("fullHouseCheckBoxId","No");
                }else {
                    data.put("fullHouseCheckBoxId",fullHouseChecked);
                }
                data.put("areaSpinnerId",areaListSpinner);
                data.put("areaUnitID",areaUnitID.getText().toString().trim());
                data.put("floorSpinnerId",floorListSpinner);
                data.put("adTitleID",adTitleID.getText().toString().trim());
                data.put("descriptionID",descriptionID.getText().toString().trim());
                newPost.setValue(data);
                Toast.makeText(SaleThirdActivity.this, "Survey Sale Added!", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
                dialog.dismiss();

                sector_nameID.setText("");
                street_numberID.setText("");
                house_numberID.setText("");
                owner_pd_numberID.setText("");
                bedroomID.setText("");
                bathID.setText("");
                tvLaunchID.setText("");
                drying_roomID.setText("");
                dinning_roomID.setText("");
                servant_roomID.setText("");
                advanceID.setText("");
                securityID.setText("");
                mobileSecondNumberID.setText("");
                rentID.setText("");
                areaUnitID.setText("");
                adTitleID.setText("");
                descriptionID.setText("");
            }
        });

        dialog = dialogBuilder.create();
        dialog.show();

    }

    public void OwnerOrPropertySpinner(){
        List<String> furnishList = new ArrayList<>();
        furnishList.add("Owner");
        furnishList.add("Property Dealer");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,furnishList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ownerorpropertyID.setAdapter(arrayAdapter);
        ownerorpropertyID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                ownerorpropertyListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void init() {
        furnishId = findViewById(R.id.furnishId);
        areaSpinnerId = findViewById(R.id.areaSpinnerId);
        floorSpinnerId = findViewById(R.id.floorSpinnerId);
        ownerorpropertyID = findViewById(R.id.ownerorpropertyID);
        rentorsaleId = findViewById(R.id.rentorsaleId);
        sector_nameID = findViewById(R.id.sector_nameID);
        street_numberID = findViewById(R.id.street_numberID);
        house_numberID = findViewById(R.id.house_numberID);
        owner_pd_numberID = findViewById(R.id.owner_pd_numberID);
        mobileSecondNumberID = findViewById(R.id.mobileSecondNumberID);
        bedroomID = findViewById(R.id.bedroomID);
        bathID = findViewById(R.id.bathID);
        adbtnID = findViewById(R.id.adbtnID);
        tvLaunchID = findViewById(R.id.tvLaunchID);
        drying_roomID = findViewById(R.id.drying_roomID);
        dinning_roomID = findViewById(R.id.dinning_roomID);
        servant_roomID = findViewById(R.id.servant_roomID);
        advanceID = findViewById(R.id.advanceID);
        securityID = findViewById(R.id.securityID);
        rentID = findViewById(R.id.rentID);
        areaUnitID = findViewById(R.id.areaUnitID);
        adTitleID = findViewById(R.id.adTitleID);
        descriptionID = findViewById(R.id.descriptionID);
        car_parkingCheckBoxID = findViewById(R.id.car_parkingCheckBoxID);
        seperate_gasCheckBoxId = findViewById(R.id.seperate_gasCheckBoxId);
        sep_elecCheckBoxId = findViewById(R.id.sep_elecCheckBoxId);
        motorBorCheckBoxID = findViewById(R.id.motorBorCheckBoxID);
        fullHouseCheckBoxId = findViewById(R.id.fullHouseCheckBoxId);
        gateSeperateCheckBoxId = findViewById(R.id.gateSeperateCheckBoxId);
        submitbuttonId = findViewById(R.id.submitbuttonId);
        nextbuttonId = findViewById(R.id.nextbuttonId);
    }

    public void furnishSpinner(){
        List<String> furnishList = new ArrayList<>();
        furnishList.add("Yes");
        furnishList.add("No");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,furnishList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        furnishId.setAdapter(arrayAdapter);
        furnishId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                furnishListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void areaSpinner(){

        List<String> areaList = new ArrayList<>();
        areaList.add("Marla");
        areaList.add("Kanal");
        areaList.add("Square Yards");
        areaList.add("Square Feet");
        areaList.add("Square Meter");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,areaList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinnerId.setAdapter(arrayAdapter);
        areaSpinnerId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                areaListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void floorSpinner(){
        List<String> floorList = new ArrayList<>();
        floorList.add("Full House");
        floorList.add("Ground + Upper");
        floorList.add("Ground + Basement");
        floorList.add("Ground");
        floorList.add("Upper");
        floorList.add("Basement");
        floorList.add("Floor Level 1");
        floorList.add("Floor Level 2");
        floorList.add("Floor Level 3");
        floorList.add("Floor Level 4");
        floorList.add("Floor Level 5");
        floorList.add("Floor Level 6+");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,floorList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorSpinnerId.setAdapter(arrayAdapter);
        floorSpinnerId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                floorListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void rentOrSaleSpinner(){
        final List<String> rentOrSaleList = new ArrayList<>();
        rentOrSaleList.add("Lakh");
        rentOrSaleList.add("Crore");
        rentOrSaleList.add("Arab");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,rentOrSaleList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rentorsaleId.setAdapter(arrayAdapter);
        rentorsaleId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                rentorsaleListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
        SaleThirdActivity.this.finish();
    }
}
