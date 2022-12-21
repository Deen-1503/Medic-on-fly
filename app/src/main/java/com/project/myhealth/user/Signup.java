package com.project.myhealth.user;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.project.myhealth.MainActivity;
import com.project.myhealth.R;

public class Signup extends AppCompatActivity {
    EditText mEmail, mPassword, mPhone;
    Button mSignupBtn;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mEmail = findViewById(R.id.signup_email);
        mPassword = findViewById(R.id.signup_password);
        mPhone = findViewById(R.id.signup_phone);

        mSignupBtn = findViewById(R.id.signup_btn);
        progressBar = findViewById(R.id.progressBar);

        mSignupBtn.setOnClickListener(view -> {
            String email = mEmail.getText().toString();
            String password = mPassword.getText().toString();
            String phone = mPhone.getText().toString();

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
            Intent loginIntent = new Intent(Signup.this,Register.class);
            loginIntent.putExtra("email",email);
            loginIntent.putExtra("password",password);
            loginIntent.putExtra("phone",phone);
            startActivity(loginIntent);

        });
    }

    public void signin(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}