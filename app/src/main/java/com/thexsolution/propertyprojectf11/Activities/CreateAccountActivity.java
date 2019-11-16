package com.thexsolution.propertyprojectf11.Activities;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.thexsolution.propertyprojectf11.R;
import com.thexsolution.propertyprojectf11.Util.ConnectionDetect;

import java.util.Date;

public class CreateAccountActivity extends AppCompatActivity {
    private Button account_btn;
    private EditText name_ID,emailID,passwordID;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReferrence;
    private ProgressDialog mProgressDialog;
    private ConnectionDetect connectionDetect;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //Casting Widget's
        account_btn = findViewById(R.id.account_btn);
        name_ID = findViewById(R.id.name_ID);
        emailID = findViewById(R.id.emailID);
        passwordID = findViewById(R.id.passwordID);
        connectionDetect = new ConnectionDetect(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReferrence = mDatabase.getReference().child("PropertyUsers");
        mProgressDialog = new ProgressDialog(this);

        account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Patterns.EMAIL_ADDRESS.matcher(emailID.getText().toString().trim()).matches() && !emailID.getText().toString().trim().isEmpty() && !name_ID.getText().toString().trim().isEmpty()
                        && !passwordID.getText().toString().isEmpty()) {
                    createAccount();
                }else {
                    Toast.makeText(CreateAccountActivity.this, "Plz put Data!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void createAccount() {
            mProgressDialog.setMessage("Creating Account...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
            mAuth.createUserWithEmailAndPassword(emailID.getText().toString().trim(),passwordID.getText().toString().trim())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                mProgressDialog.dismiss();
                                Toast.makeText(CreateAccountActivity.this, "Email Exists", Toast.LENGTH_SHORT).show();
                            }else if(task.isSuccessful()){

                                String userid = mAuth.getCurrentUser().getUid();
                                DatabaseReference currenUserDb = mDatabaseReferrence.child(userid);
                                currenUserDb.child("name").setValue(name_ID.getText().toString().trim());
                                currenUserDb.child("userid").setValue(userid);
                                currenUserDb.child("email").setValue(emailID.getText().toString().trim());
                                currenUserDb.child("usertoken").setValue(FirebaseInstanceId.getInstance().getToken() + "");
                                currenUserDb.child("pwd").setValue(passwordID.getText().toString().trim());
                                currenUserDb.child("date").setValue(new Date().getTime());
                                mProgressDialog.dismiss();
                                Toast.makeText(CreateAccountActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }

}
