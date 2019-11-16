package com.thexsolution.propertyprojectf11.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.thexsolution.propertyprojectf11.R;

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

public class InternalClickFullDataActivity extends AppCompatActivity {

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
    private TextView
            addressID, rentID, mobileNumberID, mobileNumberID2, bedroomID, bathID, tvLaunchID, dryingroomID, dinnigroomID, servantroomID,
            advanceID, securityID, rentOrSaleID, furnishId, carParkID, sepGasID, sepElecID, motorBorID, fullHouseID, gateSeperateID, areaID, areaUnitID,
            floorLevelsID, adTitleID, decriptionID, date,dealerNameID,rentOrSale,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_click_full_data);
        init();
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
        addressID.setText("Sector Name"+"\t"+sectorName + "\t" + "\t" + "Street No:" + "\t" + streetNumber + "\t" + "House No:" + "\t" + houseNumber);
        if (rentorsale.equals("Rent")){
            rentID.setText(rent + "\t" + "Rs/-");
        }else {
            rentID.setText(rent + "\t" + "Crore/-");
        }
        mobileNumberID.setText(ownerPDNumber);
        dealerNameID.setText(dealerNamePropertyOrOwner);
        mobileNumberID2.setText(ownerPDSecondNumber);
        bedroomID.setText("Bedroom:"+"\t"+bedroom);
        bathID.setText("Bath:"+"\t"+bath);
        tvLaunchID.setText("Tv Launch:"+"\t"+tvLaucnh);
        dryingroomID.setText("Dry Room:"+"\t"+dryingRoom);
        dinnigroomID.setText("Din Room:"+"\t"+dinningRoom);
        servantroomID.setText("Der Room:"+"\t"+servantRoom);
        advanceID.setText(advance);
        securityID.setText(security);
        if (rentorsale.equals("Rent")){
            rentOrSale.setText("Rent");
            type.setText("Rent");
        }else {
            rentOrSale.setText("Sale");
            type.setText("Sale");
        }
        rentOrSaleID.setText(rentorsale);
        furnishId.setText("Furnish:"+"\t"+furnish);
        carParkID.setText("Car Parking:" + "\t" + carPark);
        sepGasID.setText("Seperate Gas:" + "\t" + sepGas);
        sepElecID.setText("Seperate Electricity:" + "\t" + sepElec);
        motorBorID.setText("Motor Bor:" + "\t" + motorBor);
        fullHouseID.setText("Furnish:" + "\t" + fullHouse);
        gateSeperateID.setText("Gate Seperate:" + "\t" + gateSep);
        areaID.setText(area);
        areaUnitID.setText(areaUnit);
        floorLevelsID.setText(floorLevels);
        adTitleID.setText(adTitle);
        decriptionID.setText(description);
        date.setText("Date:"+"\t"+date_timestamp);

        getSupportActionBar().setTitle("Sec#:"+"\t"+sectorName+"\t"+"Str#:"+"\t"+streetNumber+"\t"+"H#:"+"\t"+houseNumber);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void init() {

        addressID = findViewById(R.id.addressID);
        rentID = findViewById(R.id.rentID);
        dealerNameID = findViewById(R.id.dealerNameID);
        mobileNumberID = findViewById(R.id.mobileNumberID);
        mobileNumberID2 = findViewById(R.id.mobileNumberID2);
        bedroomID = findViewById(R.id.bedroomID);
        bathID = findViewById(R.id.bathID);
        rentOrSale = findViewById(R.id.rentOrSale);
        tvLaunchID = findViewById(R.id.tvLaunchID);
        dryingroomID = findViewById(R.id.dryingroomID);
        dinnigroomID = findViewById(R.id.dinnigroomID);
        servantroomID = findViewById(R.id.servantroomID);
        securityID = findViewById(R.id.securityID);
        rentOrSaleID = findViewById(R.id.rentOrSaleID);
        furnishId = findViewById(R.id.furnishId);
        carParkID = findViewById(R.id.carParkID);
        sepGasID = findViewById(R.id.sepGasID);
        sepElecID = findViewById(R.id.sepElecID);
        motorBorID = findViewById(R.id.motorBorID);
        fullHouseID = findViewById(R.id.fullHouseID);
        gateSeperateID = findViewById(R.id.gateSeperateID);
        areaID = findViewById(R.id.areaID);
        areaUnitID = findViewById(R.id.areaUnitID);
        advanceID = findViewById(R.id.advanceID);
        floorLevelsID = findViewById(R.id.floorLevelsID);
        adTitleID = findViewById(R.id.adTitleID);
        decriptionID = findViewById(R.id.decriptionID);
        date = findViewById(R.id.date);
        type = findViewById(R.id.type);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        if(item.getItemId() == R.id.action_share){

            shareTextOnSocialMessagingApps();

        }

        return super.onOptionsItemSelected(item);
    }

    private void shareTextOnSocialMessagingApps() {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Property Detail");
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Address:\n"+sectorName
                        + "\t" + "Street No:" + "\t"
                        + streetNumber + "\t"
                        + "House No :" + "\t" + houseNumber
                        + "\nMobile Number 1 :" + ownerPDNumber
                        + "\nMobile Number 2 :" + ownerPDSecondNumber
                        + "\nBedroom :" + bedroom
                        +"\nBath:" + "\t" + bath
                        +"\nTv Launch:"+ "\t" + tvLaucnh
                        +"\nDrying Room:"+ "\t" + dryingRoom
                        +"\nDinning Room:"+ "\t" + dinningRoom
                        +"\nServant Room:"+ "\t" + servantRoom
                        +"\nAdvance:"+ "\t" + advance
                        +"\nSecurity:"+ "\t" + security
                        +"\nRent:"+ "\t" + rent
                        +"\nRent/Sale:"+ "\t" + rentorsale
                        +"\nFurnish:"+ "\t" + furnish
                        +"\nCar Parking:"+ "\t" + carPark
                        +"\nSeperate Gas Meter:"+ "\t" + sepGas
                        +"\nSeperate Electricity Meter:"+ "\t" + sepElec
                        +"\nMotor Bor:"+ "\t" + motorBor
                        +"\nFull House:"+ "\t" + fullHouse
                        +"\nSeperate Gate:"+ "\t" + gateSep
                        +"\nArea:"+ "\t" + area
                        +"\nArea Unit:"+ "\t" + areaUnit
                        +"\nFloor Levels:"+ "\t" + floorLevels
                        +"\nAd Title:"+ "\t" + adTitle
                        +"\nDescription:"+ "\t" + description
        );
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(this, "Please Exit from top!", Toast.LENGTH_SHORT).show();
        InternalClickFullDataActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_internaldatashow,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
