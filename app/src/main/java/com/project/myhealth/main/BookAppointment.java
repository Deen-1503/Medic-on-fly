package com.project.myhealth.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.project.myhealth.R;
import com.project.myhealth.superclasses.BaseActivity;
import com.project.myhealth.utils.DataSet;

public class BookAppointment extends BaseActivity implements View.OnClickListener{
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private String fUserEmail;
    private FirebaseUser fUser;
    DocumentReference docRef;

    private TextView email, fName, phone_no;
    private TextView mContext;
    private TextView t_9am, t_10am, t_11am, t_3pm;
    private TextView HPD, HKL;
    private TextView chekup, dentist, kidney, blood;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_book_appointment;
    }

    @Override
    protected boolean hasBottomNav() {
        return false;
    }

    @Override
    protected Activity getContext() {
        return BookAppointment.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        fUser = fAuth.getCurrentUser();
        fUserEmail = fUser.getEmail().toString();

        email = findViewById(R.id.book_email);
        fName = findViewById(R.id.book_fullname);
        phone_no = findViewById(R.id.book_phoneno);

        mContext = findViewById(R.id.book_context);

        t_9am = findViewById(R.id.book_time_9am);
        t_10am = findViewById(R.id.book_time_10am);
        t_11am = findViewById(R.id.book_time_11am);
        t_3pm = findViewById(R.id.book_time_3pm);

        HPD = findViewById(R.id.book_hospital_HPD);
        HKL = findViewById(R.id.book_hospital_HKL);
        chekup = findViewById(R.id.book_cat_checkup);
        dentist = findViewById(R.id.book_cat_dentist);
        kidney = findViewById(R.id.book_cat_kidney);
        blood = findViewById(R.id.book_cat_blood);

        docRef = fStore.collection("Users").document(fUserEmail);
        loadData();
    }

    private void loadData(){
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Log.e("BookAppointment","Profile Load fail: " + error.getMessage());
                    return;
                }
                email.setText(fUserEmail);
                phone_no.setText(value.getString("phoneNumber"));
                fName.setText(value.getString("fullName"));
            }
        });
    }

    private void uploadData(){
        DocumentReference upDoc = docRef.collection("Appointment").document();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.book_time_9am:

        }
    }
}