package com.project.myhealth.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.project.myhealth.R;
import com.project.myhealth.database.DataSet;
import com.project.myhealth.user.Login;

import java.util.ArrayList;

public class CustomDialog extends Dialog implements android.view.View.OnClickListener{

    private final Activity context;
    private final int resourceLayout;
    RecyclerView recyclerView;
    RVAdapter adapter;
    ArrayList<DataSet> prescription;
    Activity activity;

    public CustomDialog(Activity context, int resourceLayout) {
        super(context);
        this.context = context;
        this.resourceLayout = resourceLayout;
    }


    public CustomDialog(Activity context, int resourceLayout, RVAdapter adapter, ArrayList<DataSet> prescription, Activity activity) {
        super(context);
        this.context = context;
        this.resourceLayout = resourceLayout;
        this.adapter = adapter;
        this.prescription = prescription;
        this.activity = activity;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(resourceLayout);

        if(resourceLayout == R.layout.layout_custom_dialog){
            Button yes = (Button) findViewById(R.id.dia_btn_yes);
            Button no = (Button) findViewById(R.id.dia_btn_no);

            yes.setOnClickListener(this);
            no.setOnClickListener(this);

        } else if (resourceLayout == R.layout.layout_precripstion_dialog) {
            recyclerView = (RecyclerView) findViewById(R.id.pres_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            recyclerView.setAdapter(adapter);
            prescription.clear();
            Button close = (Button) findViewById(R.id.close);
            close.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dia_btn_yes:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(context, Login.class);
                context.startActivity(i);
                dismiss();
                context.finish();
                break;
            case R.id.dia_btn_no:
                dismiss();
                break;
            case R.id.close:
                dismiss();
                break;
            default:
                break;
        }
    }
}
