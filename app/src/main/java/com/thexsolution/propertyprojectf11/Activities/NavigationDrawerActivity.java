package com.thexsolution.propertyprojectf11.Activities;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thexsolution.propertyprojectf11.Fragments.AddSurveyFragment;
import com.thexsolution.propertyprojectf11.Fragments.ChatIndividualFragment;
import com.thexsolution.propertyprojectf11.Fragments.HomeFragment;
import com.thexsolution.propertyprojectf11.Fragments.ReminderFragment;
import com.thexsolution.propertyprojectf11.Fragments.SearchFragment;
import com.thexsolution.propertyprojectf11.Model.BottomNavigationViewHelper;
import com.thexsolution.propertyprojectf11.Model.ChatList;
import com.thexsolution.propertyprojectf11.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    android.support.v4.app.Fragment selectedFragment;
    public static final String PREFS_NAME = "preferenceName";
    public static final String KEY_NAME = "preferenceKeyName";
    public static final String KEY_EMAIL = "preferenceKeyEmail";
    public static final String VALUE_NAME = "preferenceValueName";
    public
    Toolbar toolbar;
    DatabaseReference userRef;
    ValueEventListener userDataListener;
    public static ChatList myUser;
    String[] permissions;
    private FirebaseAuth mAuth;
    public static final String ACTION_INTEGER_VALUE = "action_integer_value";
    public static final String ACTION_SPINNER_AMOUNT_VALUE = "action_spinner_amount_value";
    public static final int MULTIPLE_PERMISSIONS = 10; // code you want.
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_chat:
                    toolbar.setTitle("Chat");
                    selectedFragment = new ChatIndividualFragment();
                    //Toast.makeText(DrawerActivity.this, "Navigation", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navigation_search:
                    toolbar.setTitle("Search");
                    selectedFragment = new SearchFragment();
                    //Toast.makeText(DrawerActivity.this, "Navigation", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navigation_addsurvey:
                    toolbar.setTitle("Survey");
                    selectedFragment = new AddSurveyFragment();
                    //Toast.makeText(DrawerActivity.this, "Navigation", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.navigation_reminder:
                    toolbar.setTitle("Reminder");
                    selectedFragment = new ReminderFragment();
                    //Toast.makeText(DrawerActivity.this, "Navigation", Toast.LENGTH_SHORT).show();
                    break;

            }


            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        myUser=new ChatList();
        userDataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap hashMap = (HashMap) dataSnapshot.getValue();
                myUser.name = hashMap.get("name").toString();
                myUser.email = hashMap.get("email").toString();
                if (setPreference(NavigationDrawerActivity.this,KEY_NAME,myUser.name)){

                    if (setPreferenceEmail(NavigationDrawerActivity.this,KEY_EMAIL,myUser.email)){

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        if (FirebaseAuth.getInstance().getCurrentUser()!=null) {
            userRef = FirebaseDatabase.getInstance().getReference("PropertyUsers").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            userRef.addValueEventListener(userDataListener);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        permissions = new String[]{
                Manifest.permission.CALL_PHONE,};
        if (checkPermissions()){

        }

        mAuth = FirebaseAuth.getInstance();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new HomeFragment());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public static boolean setPreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "defaultValue");
    }

    public static boolean setPreferenceEmail(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getPreferenceEmail(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "defaultValue");
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            mAuth.signOut();
//            startActivity(new Intent(NavigationDrawerActivity.this,MainActivity.class));
//            finish();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the Profile action
            startActivity(new Intent(NavigationDrawerActivity.this,ProfileActivity.class));
        } else if (id == R.id.nav_gallery) {
            //Rate App
            rateUsOurApp(this);
        } else if (id == R.id.nav_slideshow) {
            //Share App
            shareApp(this);
        } else if (id == R.id.nav_manage) {
            //Signout From Account
            mAuth.signOut();
            startActivity(new Intent(NavigationDrawerActivity.this, MainActivity.class));
            finish();
        }
        else if (id == R.id.nav_attribution) {
            startActivity(new Intent(NavigationDrawerActivity.this,AuthorCreditActivity.class));
        }
//        else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void shareApp(Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out " + context.getResources().getString(R.string.app_name) + " app at: https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    public static void rateUsOurApp(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(NavigationDrawerActivity.this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(NavigationDrawerActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }
}
