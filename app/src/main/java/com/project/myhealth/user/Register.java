package com.project.myhealth.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.myhealth.MainActivity;
import com.project.myhealth.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private EditText mFirstName, mAddress;
    private TextView mEmail, mDOB, mPhone;
    private Spinner mCity;

    private DatePickerDialog.OnDateSetListener mOnDateListener;
    private Button mRegisterBtn;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private String fUserEmail;
    private FirebaseUser user;

    private ProgressDialog pDialog;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setTitle("Sending Data");

        mFirstName = findViewById(R.id.register_fname);
        mPhone = findViewById(R.id.register_phone);
        mAddress = findViewById(R.id.register_address);
        mEmail = findViewById(R.id.register_email);
        mCity = findViewById(R.id.register_city);
        mDOB = findViewById(R.id.register_dob);
        mRegisterBtn = findViewById(R.id.register_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.city ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCity.setAdapter(adapter);

        mCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mDOB.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    Register.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mOnDateListener,
                    year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            dialog.show();
        });
        mOnDateListener = (datePicker, year, month, day) -> {
            String date = getMonthFormat(month)  + " " + day + " " + year;
            mDOB.setText(date);
        };

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        extras = getIntent().getExtras();
        if (extras != null) {
            fUserEmail = extras.getString("email");
            mEmail.setText(fUserEmail);
            mPhone.setText(extras.getString("phone"));
        }

        mRegisterBtn.setOnClickListener(view -> {
            setRegisterBtnClicked();
        });
    }

    private void setRegisterBtnClicked(){
        pDialog.show();
        if (extras != null) {
            fAuth.createUserWithEmailAndPassword(extras.getString("email"),extras.getString("password")).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Verification Email
                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Register.this, "Verification Email Has been Send", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("FirebaseHelper Register", "OnFailure : Email Not Sent " + e.getMessage());
                            }
                        });

                        String fName = mFirstName.getText().toString();
                        String phoneNumber = mPhone.getText().toString();
                        String address = mAddress.getText().toString();
                        String city = mCity.getSelectedItem().toString();
                        String dob = mDOB.getText().toString();

                        DocumentReference document = fStore.collection("Users").document(fUserEmail);
                        Map<String,Object> userProfile = new HashMap<>();
                        userProfile.put("fullName",fName);
                        userProfile.put("phoneNumber",phoneNumber);
                        userProfile.put("address",address);
                        userProfile.put("city",city);
                        userProfile.put("dateOfBirth",dob);

                        document.set(userProfile).addOnSuccessListener(unused -> {
                            Toast.makeText(Register.this, "User Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        });
                    } else {
                        Toast.makeText(Register.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }

    private String getMonthFormat(int month) {
        if (month == 0)
            return "JAN";
        if (month == 1)
            return "FEB";
        if (month == 2)
            return "MAR";
        if (month == 3)
            return "APR";
        if (month == 4)
            return "MAY";
        if (month == 5)
            return "JUN";
        if (month == 6)
            return "JUL";
        if (month == 7)
            return "AUG";
        if (month == 8)
            return "SEP";
        if (month == 9)
            return "OCT";
        if (month == 10)
            return "NOV";
        if (month == 11)
            return "DIS";
        return "JAN";
    }

}