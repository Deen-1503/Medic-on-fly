package com.project.myhealth.main;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.project.myhealth.R;
import com.project.myhealth.database.FirebaseHelper;
import com.project.myhealth.superclasses.BaseActivity;

public class AppointmentActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_appointment;
    }

    @Override
    protected boolean hasBottomNav() {
        return false;
    }

    @Override
    protected Activity getContext() {
        return AppointmentActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FirebaseHelper firebaseHelper = FirebaseHelper.getInstance();
        RecyclerView rcylView = findViewById(R.id.appoint_view);
        firebaseHelper.loadData("Appointment", getContext(),rcylView,R.layout.layout_item_appointment);

        Button bookButton = findViewById(R.id.appoint_button);
        bookButton.setOnClickListener(v -> {
            Intent toBookintent = new Intent(AppointmentActivity.this, BookAppointment.class);
            startActivity(toBookintent);
        });

    }
}