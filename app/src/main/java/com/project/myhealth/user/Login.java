package com.project.myhealth.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.myhealth.MainActivity;
import com.project.myhealth.R;

public class Login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Retrieving Data");
        dialog.setMessage("Loading...");

        mEmail = findViewById(R.id.signin_email);
        mPassword = findViewById(R.id.signin_password);
        progressBar = findViewById(R.id.signin_progressBar);
        mLoginBtn = findViewById(R.id.signin_btn);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Required");
                    return;
                }
                if (password.length() < 6){
                    mPassword.setError("Password must more than 6 character");
                    return;
                }
//                progressBar.setVisibility(View.VISIBLE);
                dialog.show();
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(view.getWindowToken(),0);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(Login.this, MainActivity.class);
                        startActivity(loginIntent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);

                    }dialog.dismiss();
                });
            }
        });
    }

    public void signup(View view) {
        startActivity(new Intent(getApplicationContext(), Signup.class));
        finish();
    }
}