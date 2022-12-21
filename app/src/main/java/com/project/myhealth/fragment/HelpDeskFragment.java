package com.project.myhealth.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.project.myhealth.R;

public class HelpDeskFragment extends Fragment{
    //Crud
    ImageView phone;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_help_desk, container, false);

        phone = (ImageView) view.findViewById(R.id.a_phone);
        return view;
    }

}