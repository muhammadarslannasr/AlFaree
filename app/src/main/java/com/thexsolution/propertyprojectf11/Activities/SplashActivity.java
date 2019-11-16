package com.thexsolution.propertyprojectf11.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thexsolution.propertyprojectf11.R;

public class SplashActivity extends AppCompatActivity {
    private ImageView splash_appicon;
    int SPLASH_DISPLAY_LENGTH = 3000;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUSer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash_appicon = (ImageView) findViewById(R.id.splash_appicon);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_animation);
        splash_appicon.setAnimation(animation);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUSer = firebaseAuth.getCurrentUser();
                if (mUSer != null) {
                    Toast.makeText(SplashActivity.this, "Signed In", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent mainIntent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                            startActivity(mainIntent);
                            finish();
                        }

                    }, SPLASH_DISPLAY_LENGTH);
                } else {
                    Toast.makeText(SplashActivity.this, "Not Signed In", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainIntent);
                            finish();
                        }

                    }, SPLASH_DISPLAY_LENGTH);
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener !=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
