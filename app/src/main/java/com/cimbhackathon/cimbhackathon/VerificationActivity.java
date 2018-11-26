package com.cimbhackathon.cimbhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;


public class VerificationActivity extends AppCompatActivity {
    private Button btnVerify;
    private EditText username, identityCard , phoneNo, address;
    private ImageView userFace;

    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String userID;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

//        final String useremail = auth.getCurrentUser().getEmail();

        final EditText username = (EditText) findViewById(R.id.username_verification);
        final EditText identityCard = (EditText) findViewById(R.id.identityno_verification);
        final EditText phoneNo = (EditText) findViewById(R.id.phonenumber_verification);
        final EditText address = (EditText) findViewById(R.id.address_verification);
        Button btnVerify = (Button) findViewById(R.id.verified_me);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = username.getText().toString();
                String identityCardNo = identityCard.getText().toString();
                String phoneNum = phoneNo.getText().toString();
                String addressHome = address.getText().toString();


                if(TextUtils.isEmpty(userID)){
                    createUser(userName,identityCardNo,phoneNum,addressHome);
                }

            }
        });


    }
    private void createUser(String userName,String identityCard , String phoneNo , String addressHome) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userID)) {
            userID = databaseReference.push().getKey();
        }

        User user = new User(userName, identityCard , phoneNo , addressHome);

        databaseReference.child(userID).setValue(user)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Write was successful!
                // ...
                Toast.makeText(VerificationActivity.this, "Successfully verified", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(VerificationActivity.this , MainActivity.class ));
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                        Toast.makeText(VerificationActivity.this, "Failed verified", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(VerificationActivity.this , VerificationActivity.class ));
                    }
                });

    }


}
