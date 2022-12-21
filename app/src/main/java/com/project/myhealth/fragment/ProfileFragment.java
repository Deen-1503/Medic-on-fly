package com.project.myhealth.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.myhealth.R;

public class ProfileFragment extends Fragment {
    TextView userID, email, name, lastName, noPhone, dob, age, address, city;

    FirebaseUser fUser;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    View view;
    private String fUserEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        if (fAuth.getCurrentUser() != null) {
            fUserEmail = fUser.getEmail().toString();

            userID = view.findViewById(R.id.profile_id);
            email = view.findViewById(R.id.profile_email);
            name = view.findViewById(R.id.profile_name);
            noPhone = view.findViewById(R.id.profile_phoneno);
            address = view.findViewById(R.id.profile_address);
            city = view.findViewById(R.id.profile_city);
            dob = view.findViewById(R.id.profile_dob);

            DocumentReference docRef = fStore.collection("Users").document(fUserEmail);
            docRef.addSnapshotListener((value, error) -> {
                userID.setText(fUser.getUid().toString());
                email.setText(fAuth.getCurrentUser().getEmail());
                name.setText(value.getString("fullName"));
                noPhone.setText(value.getString("phoneNumber"));
                dob.setText(value.getString("dateOfBirth"));
                address.setText(value.getString("address"));
                city.setText(value.getString("city"));
            });
        }


        return view;
    }
}