package com.thexsolution.propertyprojectf11.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.thexsolution.propertyprojectf11.R;

import static com.thexsolution.propertyprojectf11.Activities.NavigationDrawerActivity.KEY_EMAIL;
import static com.thexsolution.propertyprojectf11.Activities.NavigationDrawerActivity.KEY_NAME;

public class ProfileActivity extends AppCompatActivity {
    private TextView person_nameID,person_EmailID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile Detail");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //Casting Widget's
        person_nameID = findViewById(R.id.person_nameID);
        person_EmailID = findViewById(R.id.person_EmailID);

        person_EmailID.setText(NavigationDrawerActivity.getPreferenceEmail(ProfileActivity.this,KEY_EMAIL));
        person_nameID.setText(NavigationDrawerActivity.getPreference(ProfileActivity.this,KEY_NAME));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
