package com.project.myhealth.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.myhealth.R;
import com.project.myhealth.database.FirebaseHelper;

public class ProfileFragment extends Fragment {
    TextView userID, email, name, lastName, noPhone, dob, age, address, city;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        FirebaseHelper firebaseHelper = FirebaseHelper.getInstance();
        userID = view.findViewById(R.id.profile_id);
        email = view.findViewById(R.id.profile_email);
        name = view.findViewById(R.id.profile_name);
        noPhone = view.findViewById(R.id.profile_phoneno);
        address = view.findViewById(R.id.profile_address);
        city = view.findViewById(R.id.profile_city);
        dob = view.findViewById(R.id.profile_dob);

//        userID.setText(firebaseHelper.getUserInfo("USER_ID"));
//        email.setText(firebaseHelper.getUserInfo("EMAIL"));
//        name.setText(firebaseHelper.getUserInfo("FULL_NAME"));
//        noPhone.setText(firebaseHelper.getUserInfo("PHONE_NUMBER"));
//        dob.setText(firebaseHelper.getUserInfo("DOB"));
//        address.setText(firebaseHelper.getUserInfo("ADDRESS"));
//        city.setText(firebaseHelper.getUserInfo("CITY"));

        userID.setText(firebaseHelper.getProfile().getUserID());
        email.setText(firebaseHelper.getProfile().getEmail());
        name.setText(firebaseHelper.getProfile().getFullName());
        noPhone.setText(firebaseHelper.getProfile().getPhoneNumber());
        dob.setText(firebaseHelper.getProfile().getDateOfBirth());
        address.setText(firebaseHelper.getProfile().getAddress());
        city.setText(firebaseHelper.getProfile().getCity());

        return view;
    }
}