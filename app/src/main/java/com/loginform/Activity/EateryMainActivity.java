package com.loginform.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.loginform.Database.EateryDbHelper;
import Fragment.EateryFragment;
import Fragment.LSBinhLuanFragment;
import com.loginform.R;

public class EateryMainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private EateryDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatery_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
        toolbar = getSupportActionBar();
        if(toolbar != null) {
            toolbar.hide();
        }

        dbHelper = new EateryDbHelper(this);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new EateryFragment();
                    loadFragment(fragment);
                    if(toolbar != null) {
                        toolbar.setTitle("Home");
                    }
                    return true;
                case R.id.excercise1:
                    fragment = new Welcome();
                    loadFragment(fragment);
                    if(toolbar != null) {
                        toolbar.setTitle("excercise");
                    }
                    return true;
                case R.id.excercise:
                    fragment = new LSBinhLuanFragment();
                    loadFragment(fragment);
                    if(toolbar != null) {
                        toolbar.setTitle("excercise1");
                    }
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}