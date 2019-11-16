package com.thexsolution.propertyprojectf11.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.thexsolution.propertyprojectf11.R;
import com.thexsolution.propertyprojectf11.Util.ConnectionDetect;
import com.thexsolution.propertyprojectf11.Util.Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetPasswordActivity extends AppCompatActivity {

    private Button reset;
    private EditText email;
    private FirebaseAuth mAuth;
    ConnectionDetect connectionDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        getSupportActionBar().setTitle(Utility.getStringFromRes(ResetPasswordActivity.this,R.string.reset_password));

        connectionDetect = new ConnectionDetect(this);
        email = (EditText) findViewById(R.id.email);
        reset = (Button) findViewById(R.id.reset);
        mAuth = FirebaseAuth.getInstance();


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String em = email.getText().toString();
                if(em.equals("") || !isEmailValid(em)){
                    Toast.makeText(ResetPasswordActivity.this, "Plz Put Email or format is not Valid!", Toast.LENGTH_SHORT).show();
                }else {
                    if (connectionDetect.isConnected()) {
                        mAuth.sendPasswordResetEmail(em)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            //Log.d("TAG", "Email Sent!");
                                            Toast.makeText(ResetPasswordActivity.this, "Email Sent", Toast.LENGTH_SHORT).show();
                                        } else {
                                            //Log.d("TAG", "Email Not Sent");
                                            Toast.makeText(ResetPasswordActivity.this, "Kindly check your Email and try again", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                    }else{
                        Toast.makeText(ResetPasswordActivity.this, "Turn on your internet connection before Email sent.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    public void onBackPressed() {
        ResetPasswordActivity.this.finish();
    }
}
