package com.project.myhealth.main;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.myhealth.R;
import com.project.myhealth.superclasses.BaseActivity;
import com.project.myhealth.utils.DataSet;
import com.project.myhealth.utils.RVAdapter;

import java.util.ArrayList;

public class AppointmentActivity extends BaseActivity {
    private RecyclerView rcylView;
    private Button bookButton;

    private RVAdapter adapter;
    private ArrayList<DataSet> appointmentArrayList;

    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;

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

        fStore = FirebaseFirestore.getInstance();

        fAuth = FirebaseAuth.getInstance();
        appointmentArrayList = new ArrayList<DataSet>();
        adapter = new RVAdapter(AppointmentActivity.this,appointmentArrayList,R.layout.layout_item_appointment);
        rcylView = findViewById(R.id.appoint_view);
        rcylView.setHasFixedSize(true);
        rcylView.setLayoutManager(new LinearLayoutManager(this));
        rcylView.setAdapter(adapter);
        loadData();

        bookButton = findViewById(R.id.appoint_button);
        bookButton.setOnClickListener(v -> {
            Intent toBookintent = new Intent(AppointmentActivity.this, BookAppointment.class);
            startActivity(toBookintent);
        });

    }

    private void loadData() {

        Query colRef = fStore.collection("Users")
                .document(fAuth.getCurrentUser().getEmail())
                .collection("Appointment")
                .orderBy("time", CollectionReference.Direction.ASCENDING);

        colRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value,
                                @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Log.e("Firestore",error.getMessage());
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()){
                    Log.d("Added", "New city: " + dc.getDocument().getData());
                        switch (dc.getType()){
                            case ADDED:
                                appointmentArrayList.add(dc.getDocument().toObject(DataSet.class));
                                break;

                            case MODIFIED:
                                Log.d("modified", "New city: " + dc.getDocument().getData());
                                appointmentArrayList.remove(dc.getOldIndex());
                                adapter.notifyItemChanged(dc.getOldIndex());
                                break;
                            case REMOVED:
                                appointmentArrayList.remove(dc.getOldIndex());

                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + dc.getType());
                        }adapter.notifyDataSetChanged();
                    Log.d("Added", "New 1: " + appointmentArrayList);
                }
            }
        });

    }

}