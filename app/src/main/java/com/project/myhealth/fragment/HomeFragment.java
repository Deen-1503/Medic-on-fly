package com.project.myhealth.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.project.myhealth.R;
import com.project.myhealth.main.PrecripstionLoad;
import com.project.myhealth.utils.CustomDialog;
import com.project.myhealth.utils.DataSet;
import com.project.myhealth.utils.RVAdapter;

import java.util.ArrayList;
import java.util.Calendar;


public class HomeFragment extends Fragment {
    private TextView date,date1,appointmentName,noMed;
    RecyclerView recyclerView, recycleView2;
    LinearLayout prescription;
    View view;
    RVAdapter adapter, adapter2;
    ArrayList<DataSet> precripstions, appointments;

    FirebaseAuth fAuth;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        //date Setter
        date = (TextView) view.findViewById(R.id.home_date);
        setTodayDate(date);

        date1 = view.findViewById(R.id.home_date1);
        setTodayDate(date1);

        appointmentName = view.findViewById(R.id.home_appointment_name);
        fAuth = FirebaseAuth.getInstance();
        setAppointmentName();

        precripstions = new ArrayList<>();
        adapter = new RVAdapter(getActivity(), precripstions, R.layout.layout_item_precripstion);

        recyclerView = view.findViewById(R.id.pres_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        noMed = view.findViewById(R.id.home_no_med);
        prescription = view.findViewById(R.id.home_precripstion);
        PrecripstionLoad load = new PrecripstionLoad(adapter,precripstions,"Precripstion");
        load.loadData();

        if(precripstions != null){ //not empty
            noMed.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            prescription.setOnClickListener(v -> {
                CustomDialog dialog = new CustomDialog(getActivity(), R.layout.layout_precripstion_dialog, adapter, precripstions,getActivity());
                dialog.show();
                precripstions.clear();
            });
        }
        appointments = new ArrayList<>();
        adapter2 = new RVAdapter(getActivity(),appointments, R.layout.layout_item_appointment_small);

        PrecripstionLoad load2 = new PrecripstionLoad(adapter2, appointments,"Appointment");
        load2.loadData();

        recycleView2 = view.findViewById(R.id.recycle_view2);
        recycleView2.setHasFixedSize(true);
        recycleView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView2.setAdapter(adapter2);
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



//    private void setMed(QuerySnapshot snapshots){
//        if (snapshots.getDocuments().isEmpty()) {
//            noMed.setVisibility(View.VISIBLE);
//            recyclerView.setVisibility(View.INVISIBLE);
//        } else {
//            noMed.setVisibility(View.INVISIBLE);
//            recyclerView.setVisibility(View.VISIBLE);
//        }
//    }
