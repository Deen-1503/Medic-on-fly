package com.project.myhealth.utils;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.myhealth.R;

public class RVViewHolder extends RecyclerView.ViewHolder{
    TextView pMedicine,pAmount;
    TextView title, category, day, date, location, doctor;
    TextView pTitle, pTime;

    public RVViewHolder(@NonNull View itemView) {
        super(itemView);

        pMedicine = itemView.findViewById(R.id.pres_medicine);
        pAmount = itemView.findViewById(R.id.pres_take);

        title = itemView.findViewById(R.id.appoint_title);
        category = itemView.findViewById(R.id.appoint_category);
        day = itemView.findViewById(R.id.appoint_day);
        date = itemView.findViewById(R.id.appoint_date);
        location= itemView.findViewById(R.id.appoint_loc);
        doctor = itemView.findViewById(R.id.appoint_doc);

        pTime = itemView.findViewById(R.id.appt_time);
        pTitle = itemView.findViewById(R.id.appt_title);
    }
}
