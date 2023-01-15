package com.project.myhealth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.project.myhealth.main.AppointmentActivity;
import com.project.myhealth.superclasses.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean hasBottomNav() {
        return true;
    }

    @Override
    protected Activity getContext() {
        return MainActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void appointment(View view) {
        Intent intent = new Intent(this, AppointmentActivity.class);
        startActivity(intent);
    }

    public void call(View view) {
        String mPhoneNo = "+60135571004";

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + mPhoneNo));
        startActivity(callIntent);
    }

}