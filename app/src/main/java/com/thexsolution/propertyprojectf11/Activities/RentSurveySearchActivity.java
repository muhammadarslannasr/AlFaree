package com.thexsolution.propertyprojectf11.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thexsolution.propertyprojectf11.Adapters.SurveyRentRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.R;
import com.thexsolution.propertyprojectf11.Util.Utility;

import static com.thexsolution.propertyprojectf11.Activities.NavigationDrawerActivity.ACTION_INTEGER_VALUE;

public class RentSurveySearchActivity extends AppCompatActivity {
    private EditText dataRentSearchID;
    private Button searchRent;
    private SurveyRentRecyclerViewAdapter surveyRentRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_survey_search);
        getSupportActionBar().setTitle("Search Rent");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        dataRentSearchID = findViewById(R.id.dataRentSearchID);
        searchRent = findViewById(R.id.searchRent);

        searchRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RentSurveySearchActivity.this,RentSurveySearchRecyclerViewActivity.class);
                intent.putExtra(ACTION_INTEGER_VALUE,Integer.parseInt(dataRentSearchID.getText().toString().trim()));
                startActivity(intent);
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
        RentSurveySearchActivity.this.finish();
    }
}
