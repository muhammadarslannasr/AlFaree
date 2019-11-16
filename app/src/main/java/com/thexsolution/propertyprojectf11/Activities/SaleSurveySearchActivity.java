package com.thexsolution.propertyprojectf11.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.thexsolution.propertyprojectf11.R;

import java.util.ArrayList;
import java.util.List;

import static com.thexsolution.propertyprojectf11.Activities.NavigationDrawerActivity.ACTION_INTEGER_VALUE;
import static com.thexsolution.propertyprojectf11.Activities.NavigationDrawerActivity.ACTION_SPINNER_AMOUNT_VALUE;

public class SaleSurveySearchActivity extends AppCompatActivity {
    private EditText dataRentSearchID;
    private Spinner amountSaleID;
    private Button searchRent;
    String rentorsaleListSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_survey_search);
        getSupportActionBar().setTitle("Search Sale");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        dataRentSearchID = findViewById(R.id.dataRentSearchID);
        amountSaleID = findViewById(R.id.amountSaleID);
        searchRent = findViewById(R.id.searchRent);
        rentOrSaleSpinner();

        searchRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataRentSearchID.getText().toString().isEmpty()){
                    Toast.makeText(SaleSurveySearchActivity.this, "Amount Value not be Empty!", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(SaleSurveySearchActivity.this,SaleSurveySearchRecyclerViewActivity.class);
                    intent.putExtra(ACTION_INTEGER_VALUE,Integer.parseInt(dataRentSearchID.getText().toString().trim()));
                    intent.putExtra(ACTION_SPINNER_AMOUNT_VALUE,rentorsaleListSpinner);
                    startActivity(intent);
                }
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
        amountSaleID.setAdapter(arrayAdapter);
        amountSaleID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        SaleSurveySearchActivity.this.finish();
    }
}
