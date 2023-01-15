package com.project.myhealth.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Timestamp;
import com.project.myhealth.R;
import com.project.myhealth.database.DataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    private final ArrayList<DataSet> list;

    static Timestamp timestamp;
    int layout;

    public RVAdapter(Context context, ArrayList<DataSet> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(context).inflate(layout , parent,false);
        return new RVViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RVViewHolder viewHolder = (RVViewHolder) holder;
        DataSet data = list.get(position);

        if(layout == R.layout.layout_item_precripstion){
            String amount = "Take " + data.getAmount() + " pill . " + data.getTake() + "/day";
            viewHolder.pMedicine.setText(data.getMedicine());
            viewHolder.pAmount.setText(amount);

        }

        else if(layout == R.layout.layout_item_appointment) {
            //Appointment
            timestamp = data.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yy", Locale.getDefault());
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEE", Locale.getDefault());

            viewHolder.date.setText(dateFormat.format(timestamp.toDate()).toUpperCase());
            viewHolder.day.setText(dayFormat.format(timestamp.toDate()).toUpperCase());
            viewHolder.title.setText(data.getTitle());
            viewHolder.category.setText(data.getCategory());
            viewHolder.doctor.setText(data.getDoctor());
            viewHolder.location.setText(data.getLocation());
        }

        else if(layout == R.layout.layout_item_appointment_small){
            timestamp = data.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yy", Locale.getDefault());
            SimpleDateFormat time = new SimpleDateFormat("HH mm",Locale.getDefault());
            String ttime = dateFormat.format(timestamp.toDate()) + " at " + time.format(timestamp.toDate());

            viewHolder.pTitle.setText(data.getTitle());
            viewHolder.pTime.setText(ttime);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
