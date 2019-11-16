package com.thexsolution.propertyprojectf11.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.thexsolution.propertyprojectf11.R;
import com.thexsolution.propertyprojectf11.Util.Utility;

import java.util.ArrayList;
import java.util.List;

public class DashbaordActivity extends AppCompatActivity {

    private LinearLayout surveyID,rentID,surveysaleID,saledataViewID,rentreminderID,salereminderID,commercialID,quitID;
    private FirebaseAuth mAuth;
    String[] permissions;
    public static final int MULTIPLE_PERMISSIONS = 10; // code you want.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbaord);
        permissions = new String[]{
                Manifest.permission.CALL_PHONE,};
        if (checkPermissions()){

        }
        getSupportActionBar().setTitle(Utility.getStringFromRes(DashbaordActivity.this,R.string.dashbaord));
        surveyID = findViewById(R.id.surveyID);
        rentID = findViewById(R.id.rentID);
        surveysaleID = findViewById(R.id.surveysaleID);
        saledataViewID = findViewById(R.id.saledataViewID);
        rentreminderID = findViewById(R.id.rentreminderID);
        salereminderID = findViewById(R.id.salereminderID);
        commercialID = findViewById(R.id.commercialID);
        quitID = findViewById(R.id.quitID);
        mAuth = FirebaseAuth.getInstance();

        commercialID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashbaordActivity.this,RentSurveySearchActivity.class));
            }
        });

        quitID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashbaordActivity.this,SaleSurveySearchActivity.class));
            }
        });

        salereminderID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashbaordActivity.this,SaleReminderActivity.class));
            }
        });

        rentreminderID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashbaordActivity.this,RentReminderActivity.class));
            }
        });

        surveysaleID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashbaordActivity.this,SaleActivity.class));
            }
        });

        saledataViewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashbaordActivity.this,SaleDataViewActivity.class));
            }
        });

        surveyID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashbaordActivity.this,SurveyRentActivity.class));
            }
        });

        rentID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashbaordActivity.this,RentDataViewActivity.class));
            }
        });



    }



    @Override
    public void onBackPressed() {
        DashbaordActivity.this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_signout){
            mAuth.signOut();
            startActivity(new Intent(DashbaordActivity.this,MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(DashbaordActivity.this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(DashbaordActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }
}
