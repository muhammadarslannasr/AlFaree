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

import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_ADVANCE;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_AD_TITLE;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_AREA;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_AREA_UNIT;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_BATH;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_BEDROOM;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_CAR_PARK;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_DATE;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_DESCRIPTION;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_DINNING_ROOM;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_DRYING_ROOM;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_FLOOR_LEVELS;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_FULL_HOUSE;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_FURNISH;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_GATE_SEP;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_HOUSE_NUMBER;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_MOTOR_BOR;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_NODE_ID;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_ONWER_OR_PROPERTY_NUMBER;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_OWNER_PD_NUMBER;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_OWNER_SECOND_NUMBER;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_RENT;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_RENT_OR_SALE;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_SECTOR_NAME;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_SECURITY;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_SEP_ELEC;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_SEP_GAS;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_SERVANT_ROOM;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_STREET_NUMBER;
import static com.thexsolution.propertyprojectf11.Adapters.SurveyRecyclerViewAdapter.ACTION_TV_LAUNCH;

public class RentUpdateActivity extends AppCompatActivity {
    private EditText sector_nameID,street_numberID,house_numberID,owner_pd_numberID,bedroomID,bathID,tvLaunchID,drying_roomID,dinning_roomID;
    private EditText servant_roomID,advanceID,securityID,rentID,areaUnitID,adTitleID,descriptionID,mobileSecondNumberID;
    private CheckBox car_parkingCheckBoxID,seperate_gasCheckBoxId,sep_elecCheckBoxId,motorBorCheckBoxID,fullHouseCheckBoxId,gateSeperateCheckBoxId;
    private Button submitbuttonId;
    private Spinner furnishId,areaSpinnerId,floorSpinnerId,rentorsaleId,ownerorpropertyID;
    private ImageView adbtnID;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialogOld;
    private String sectorName;
    private String streetNumber;
    private String houseNumber;
    private String ownerPDNumber;
    private String bedroom;
    private String bath;
    private String tvLaucnh;
    private String dryingRoom;
    private String dinningRoom;
    private String servantRoom;
    private String advance;
    private String security;
    private String ownerPDSecondNumber;
    private String rent;
    private String rentorsale;
    private String furnish;
    private String carPark;
    private String sepGas;
    private String sepElec;
    private String motorBor;
    private String fullHouse;
    private String gateSep;
    private String area;
    private String areaUnit;
    private String floorLevels;
    private String adTitle;
    private String description;
    private String date_timestamp;
    private String dealerNamePropertyOrOwner;
    public boolean isClickedFirstTime = true;
    String furnishListSpinner;
    String areaListSpinner;
    String floorListSpinner;
    String rentorsaleListSpinner;
    String ownerorpropertyListSpinner;
    String carCheckBoxChecked = null;
    String seperategasChecked = null;
    String sepElecChecked = null;
    String motorBorChecked = null;
    String fullHouseChecked = null;
    String gateSeperateChecked = null;
    String nodeID = null;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReferrence;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_update);
        getSupportActionBar().setTitle(Utility.getStringFromRes(RentUpdateActivity.this,R.string.update_rent));
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        init();

        nodeID = getIntent().getStringExtra(ACTION_NODE_ID);
        sectorName = getIntent().getStringExtra(ACTION_SECTOR_NAME);
        streetNumber = getIntent().getStringExtra(ACTION_STREET_NUMBER);
        houseNumber = getIntent().getStringExtra(ACTION_HOUSE_NUMBER);
        ownerPDNumber = getIntent().getStringExtra(ACTION_OWNER_PD_NUMBER);
        bedroom = getIntent().getStringExtra(ACTION_BEDROOM);
        bath = getIntent().getStringExtra(ACTION_BATH);
        tvLaucnh = getIntent().getStringExtra(ACTION_TV_LAUNCH);
        dryingRoom = getIntent().getStringExtra(ACTION_DRYING_ROOM);
        dinningRoom = getIntent().getStringExtra(ACTION_DINNING_ROOM);
        servantRoom = getIntent().getStringExtra(ACTION_SERVANT_ROOM);
        advance = getIntent().getStringExtra(ACTION_ADVANCE);
        security = getIntent().getStringExtra(ACTION_SECURITY);
        ownerPDSecondNumber = getIntent().getStringExtra(ACTION_OWNER_SECOND_NUMBER);
        rent = getIntent().getStringExtra(ACTION_RENT);
        rentorsale = getIntent().getStringExtra(ACTION_RENT_OR_SALE);
        furnish = getIntent().getStringExtra(ACTION_FURNISH);
        carPark = getIntent().getStringExtra(ACTION_CAR_PARK);
        sepGas = getIntent().getStringExtra(ACTION_SEP_GAS);
        sepElec = getIntent().getStringExtra(ACTION_SEP_ELEC);
        motorBor = getIntent().getStringExtra(ACTION_MOTOR_BOR);
        fullHouse = getIntent().getStringExtra(ACTION_FULL_HOUSE);
        gateSep = getIntent().getStringExtra(ACTION_GATE_SEP);
        area = getIntent().getStringExtra(ACTION_AREA);
        areaUnit = getIntent().getStringExtra(ACTION_AREA_UNIT);
        floorLevels = getIntent().getStringExtra(ACTION_FLOOR_LEVELS);
        adTitle = getIntent().getStringExtra(ACTION_AD_TITLE);
        description = getIntent().getStringExtra(ACTION_DESCRIPTION);
        date_timestamp = getIntent().getStringExtra(ACTION_DATE);
        dealerNamePropertyOrOwner = getIntent().getStringExtra(ACTION_ONWER_OR_PROPERTY_NUMBER);
        sectorName = getIntent().getStringExtra(ACTION_SECTOR_NAME);
        streetNumber = getIntent().getStringExtra(ACTION_STREET_NUMBER);
        houseNumber = getIntent().getStringExtra(ACTION_HOUSE_NUMBER);
        ownerPDNumber = getIntent().getStringExtra(ACTION_OWNER_PD_NUMBER);
        bedroom = getIntent().getStringExtra(ACTION_BEDROOM);
        bath = getIntent().getStringExtra(ACTION_BATH);
        tvLaucnh = getIntent().getStringExtra(ACTION_TV_LAUNCH);
        dryingRoom = getIntent().getStringExtra(ACTION_DRYING_ROOM);
        dinningRoom = getIntent().getStringExtra(ACTION_DINNING_ROOM);
        servantRoom = getIntent().getStringExtra(ACTION_SERVANT_ROOM);
        advance = getIntent().getStringExtra(ACTION_ADVANCE);
        security = getIntent().getStringExtra(ACTION_SECURITY);
        ownerPDSecondNumber = getIntent().getStringExtra(ACTION_OWNER_SECOND_NUMBER);
        rent = getIntent().getStringExtra(ACTION_RENT);
        rentorsale = getIntent().getStringExtra(ACTION_RENT_OR_SALE);
        furnish = getIntent().getStringExtra(ACTION_FURNISH);
        carPark = getIntent().getStringExtra(ACTION_CAR_PARK);
        sepGas = getIntent().getStringExtra(ACTION_SEP_GAS);
        sepElec = getIntent().getStringExtra(ACTION_SEP_ELEC);
        motorBor = getIntent().getStringExtra(ACTION_MOTOR_BOR);
        fullHouse = getIntent().getStringExtra(ACTION_FULL_HOUSE);
        gateSep = getIntent().getStringExtra(ACTION_GATE_SEP);
        area = getIntent().getStringExtra(ACTION_AREA);
        areaUnit = getIntent().getStringExtra(ACTION_AREA_UNIT);
        floorLevels = getIntent().getStringExtra(ACTION_FLOOR_LEVELS);
        adTitle = getIntent().getStringExtra(ACTION_AD_TITLE);
        description = getIntent().getStringExtra(ACTION_DESCRIPTION);
        date_timestamp = getIntent().getStringExtra(ACTION_DATE);
        dealerNamePropertyOrOwner = getIntent().getStringExtra(ACTION_ONWER_OR_PROPERTY_NUMBER);

        sector_nameID.setText(sectorName);
        street_numberID.setText(streetNumber);
        house_numberID.setText(houseNumber);
        owner_pd_numberID.setText(ownerPDNumber);
        mobileSecondNumberID.setText(ownerPDSecondNumber);
        bedroomID.setText(bedroom);
        bathID.setText(bath);
        tvLaunchID.setText(tvLaucnh);
        drying_roomID.setText(dryingRoom);
        dinning_roomID.setText(dinningRoom);
        advanceID.setText(advance);
        securityID.setText(security);
        rentID.setText(rent);
        if (carPark.equals("Yes")){
            car_parkingCheckBoxID.setChecked(true);
        }else {
            car_parkingCheckBoxID.setChecked(false);
        }

        if (sepGas.equals("Yes")){
            seperate_gasCheckBoxId.setChecked(true);
        }else {
            seperate_gasCheckBoxId.setChecked(false);
        }

        if (sepElec.equals("Yes")){
            sep_elecCheckBoxId.setChecked(true);
        }else {
            sep_elecCheckBoxId.setChecked(false);
        }

        if (motorBor.equals("Yes")){
            motorBorCheckBoxID.setChecked(true);
        }else {
            motorBorCheckBoxID.setChecked(false);
        }

        if (fullHouse.equals("Yes")){
            fullHouseCheckBoxId.setChecked(true);
        }else {
            fullHouseCheckBoxId.setChecked(false);
        }

        if (gateSep.equals("Yes")){
            gateSeperateCheckBoxId.setChecked(true);
        }else {
            gateSeperateCheckBoxId.setChecked(false);
        }


        servant_roomID.setText(servantRoom);
        areaUnitID.setText(areaUnit);
        adTitleID.setText(adTitle);
        descriptionID.setText(description);

        mProgressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabaseReferrence = FirebaseDatabase.getInstance().getReference().child("SurveyData").child(mUser.getUid()).child(nodeID);

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
        dialogBuilder.setMessage("Are you Sure you want to Update!");
        dialogBuilder.setPositiveButton(Utility.getStringFromRes(RentUpdateActivity.this, R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mProgressDialog.setMessage("Update Data....");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();

                //DatabaseReference newPost = mDatabaseReferrence.push();
                String ID = mDatabaseReferrence.getKey();
                Map<String,String> data = new HashMap<>();
                data.put("sector_nameID",sector_nameID.getText().toString().trim());
                data.put("street_numberID",street_numberID.getText().toString().trim());
                data.put("timestamp",String.valueOf(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())));
                data.put("survey_nodeID",ID);
                data.put("house_numberID",house_numberID.getText().toString().trim());
                data.put("ownerorpropertyID",ownerorpropertyListSpinner);
                data.put("owner_pd_numberID",owner_pd_numberID.getText().toString().trim());
                data.put("bedroomID",bedroomID.getText().toString().trim());
                data.put("bathID",bathID.getText().toString().trim());
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
                data.put("car_parkingCheckBoxID",carCheckBoxChecked);
                data.put("seperate_gasCheckBoxId",gateSeperateChecked);
                data.put("sep_elecCheckBoxId",sepElecChecked);
                data.put("motorBorCheckBoxID",motorBorChecked);
                data.put("gateSeperateCheckBoxId",gateSeperateChecked);
                data.put("fullHouseCheckBoxId",fullHouseChecked);
                data.put("areaSpinnerId",areaListSpinner);
                data.put("areaUnitID",areaUnitID.getText().toString().trim());
                data.put("floorSpinnerId",floorListSpinner);
                data.put("adTitleID",adTitleID.getText().toString().trim());
                data.put("descriptionID",descriptionID.getText().toString().trim());
                mDatabaseReferrence.setValue(data);
                Toast.makeText(RentUpdateActivity.this, "Rent Updated!", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
                dialog.dismiss();
            }
        });
        dialogBuilder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogOld.dismiss();
            }
        });

        dialogOld = dialogBuilder.create();
        dialogOld.show();

    }

    private void init() {
        furnishId = findViewById(R.id.furnishId);
        areaSpinnerId = findViewById(R.id.areaSpinnerId);
        floorSpinnerId = findViewById(R.id.floorSpinnerId);
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
        ownerorpropertyID = findViewById(R.id.ownerorpropertyID);
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
        floorList.add("Basement");
        floorList.add("Ground");
        floorList.add("Upper");
        floorList.add("Floor Level 1");
        floorList.add("Floor Level 2");
        floorList.add("Floor Level 3");
        floorList.add("Floor Level 4");
        floorList.add("Floor Level 5");
        floorList.add("Floor Level 6");
        floorList.add("Floor Level 7");
        floorList.add("Floor Level 8");
        floorList.add("Floor Level 9");
        floorList.add("Floor Level 10");
        floorList.add("Floor Level 11");
        floorList.add("Floor Level 12");
        floorList.add("Floor Level 13");
        floorList.add("Floor Level 14");
        floorList.add("Floor Level 15");
        floorList.add("Floor Level 16");
        floorList.add("Floor Level 17");
        floorList.add("Floor Level 18");
        floorList.add("Floor Level 19");
        floorList.add("Floor Level 20");
        floorList.add("Floor Level 21");
        floorList.add("Floor Level 22");
        floorList.add("Floor Level 23");
        floorList.add("Floor Level 24");
        floorList.add("Floor Level 25");
        floorList.add("Floor Level 26");
        floorList.add("Floor Level 27");
        floorList.add("Floor Level 28");
        floorList.add("Floor Level 29");
        floorList.add("Floor Level 30");
        floorList.add("Floor Level 31");
        floorList.add("Floor Level 32");
        floorList.add("Floor Level 33");
        floorList.add("Floor Level 34");
        floorList.add("Floor Level 35");
        floorList.add("Floor Level 36");
        floorList.add("Floor Level 37");
        floorList.add("Floor Level 38");
        floorList.add("Floor Level 39");
        floorList.add("Floor Level 40");
        floorList.add("Floor Level 41");
        floorList.add("Floor Level 42");
        floorList.add("Floor Level 43");
        floorList.add("Floor Level 44");
        floorList.add("Floor Level 45");
        floorList.add("Floor Level 46");
        floorList.add("Floor Level 47");
        floorList.add("Floor Level 48");
        floorList.add("Floor Level 49");
        floorList.add("Floor Level 50");
        floorList.add("Floor Level 51");
        floorList.add("Floor Level 52");
        floorList.add("Floor Level 53");
        floorList.add("Floor Level 54");
        floorList.add("Floor Level 55");
        floorList.add("Floor Level 56");
        floorList.add("Floor Level 57");
        floorList.add("Floor Level 58");
        floorList.add("Floor Level 59");
        floorList.add("Floor Level 60");
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

        rentOrSaleList.add("Rent");
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
        RentUpdateActivity.this.finish();
    }
}
