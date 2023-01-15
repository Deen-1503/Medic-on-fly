package com.project.myhealth.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.project.myhealth.R;
import com.project.myhealth.database.DataSet;
import com.project.myhealth.database.FirebaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class HomeFragment extends Fragment {
    private TextView date,date1,appointmentName,noMed;
    private RecyclerView recyclerView, recycleView2;

    FirebaseAuth fAuth;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //date Setter
        date = (TextView) view.findViewById(R.id.home_date);
        setTodayDate(date);

        date1 = view.findViewById(R.id.home_date1);
        setTodayDate(date1);

        appointmentName = view.findViewById(R.id.home_appointment_name);
        fAuth = FirebaseAuth.getInstance();
        setAppointmentName();


        recyclerView = view.findViewById(R.id.pres_view);
        noMed = view.findViewById(R.id.home_no_med);
        LinearLayout prescription = view.findViewById(R.id.home_precripstion);

        recyclerView.setVisibility(View.VISIBLE);
        FirebaseHelper fHelper = FirebaseHelper.getInstance();
        fHelper.loadData("Precripstion", getContext(),recyclerView,R.layout.layout_item_precripstion);

        recycleView2 = view.findViewById(R.id.recycle_view2);
        fHelper.loadData("Appointment", getContext(),recycleView2,R.layout.layout_item_appointment_small);
        Log.e("Home", "onCreateView: " + fHelper.getProfile().getFullName());
        return view;
    }

    public void setAppointmentName() {

    }

    public void setTodayDate(TextView textView){
        Calendar c = Calendar.getInstance();
        String sDate = setMonth(c.get(Calendar.MONTH)) + " / " + c.get(Calendar.DAY_OF_MONTH) + " / " + c.get(Calendar.YEAR);
        textView.setText(sDate);
    }

    private String setMonth(int month){
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

