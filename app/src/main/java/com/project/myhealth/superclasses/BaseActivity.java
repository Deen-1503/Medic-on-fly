package com.project.myhealth.superclasses;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.project.myhealth.R;
import com.project.myhealth.fragment.HelpDeskFragment;
import com.project.myhealth.fragment.HomeFragment;
import com.project.myhealth.fragment.ProfileFragment;
import com.project.myhealth.utils.CustomDialog;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutResource();
    protected abstract boolean hasBottomNav();
    protected Toolbar topNav;

    protected abstract Activity getContext();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        //navigation
        if(hasBottomNav()){
            setMyBottomNav(savedInstanceState);
        }

        setMyTopNav();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setMyTopNav() {
        topNav = findViewById(R.id.top_nav);
        topNav.setTitleTextColor(Color.DKGRAY);
        setSupportActionBar(topNav);

        topNav.setOnMenuItemClickListener(item -> {
            CustomDialog dialog = new CustomDialog(getContext(),R.layout.layout_custom_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
//            FirebaseAuth.getInstance().signOut();
//            Intent i = new Intent(getContext(), Login.class);
//            startActivity(i);
//            finish();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top,menu);
        return true;
    }

    protected void setMyBottomNav(Bundle savedInstanceState){

        ChipNavigationBar myToolbar = findViewById(R.id.my_toolbar);

        if(savedInstanceState == null){
            myToolbar.setItemSelected(R.id.menu_home,true);
            HomeFragment home = new HomeFragment();
            fragmentManager(home);
        }

       myToolbar.setOnItemSelectedListener(id -> {
           Fragment frag = null;
            switch (id){
                case R.id.menu_home:
                    frag = new HomeFragment();
                    break;
                case R.id.menu_profile:
                    frag = new ProfileFragment();
                    break;
                case R.id.menu_help_desk:
                    frag = new HelpDeskFragment();
                    break;
            }

            if(frag != null){
                fragmentManager(frag);
            }
       });
    }
    private void fragmentManager(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.setCustomAnimations(R.anim.fade_out,R.anim.fade_in);
        transaction.replace(R.id.main_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
