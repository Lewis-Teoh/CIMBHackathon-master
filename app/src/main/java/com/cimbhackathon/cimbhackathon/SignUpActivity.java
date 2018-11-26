package com.cimbhackathon.cimbhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText inputEmail , inputPassword;
    private Button btnSignIn , btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email_register);
        inputPassword = (EditText) findViewById(R.id.password_register);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnSignIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openLoginActivity();

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter email address!" , Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!" , Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"Your password is too short!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                auth.createUserWithEmailAndPassword(email , password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                                Toast.makeText(SignUpActivity.this , "createUserWithEmail:onComplete" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.VISIBLE);

                                if(!task.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this, "AuthenticationFailed"+task.getException(), Toast.LENGTH_SHORT).show();

                                }else{
                                    startActivity(new Intent(SignUpActivity.this , LoginActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }
    public void openLoginActivity(){
        Intent intent = new Intent(this , LoginActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume(){
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}
