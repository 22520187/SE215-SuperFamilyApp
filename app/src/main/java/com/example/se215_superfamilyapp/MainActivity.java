package com.example.se215_superfamilyapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.se215_superfamilyapp.Fragment.CalendarFragment;
import com.example.se215_superfamilyapp.Fragment.MessageFragment;
import com.example.se215_superfamilyapp.Fragment.MissionFragment;
import com.example.se215_superfamilyapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Fragment selectorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = binding.bottomNavigation;

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_message) {
                selectorFragment = new MessageFragment();
            } else if (itemId == R.id.nav_mission) {
                 selectorFragment = new MissionFragment();
            } else if (itemId == R.id.nav_dashboard) {
                // selectorFragment = new DashboardFragment();
            } else if (itemId == R.id.nav_calendar) {
                 selectorFragment = new CalendarFragment();
            } else if (itemId == R.id.nav_smart_money) {

            }

            if (selectorFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectorFragment)
                        .commit();
            }
            return true;
        });

        // Hiển thị Fragment mặc định hoặc Fragment được chỉ định qua Intent
        if (savedInstanceState == null) {
            String fragmentToOpen = getIntent().getStringExtra("openFragment");
            if (fragmentToOpen != null && fragmentToOpen.equals("MessageFragment")) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new MessageFragment())
                        .commit();
                bottomNavigationView.setSelectedItemId(R.id.nav_message);
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new MessageFragment())
                        .commit();
                bottomNavigationView.setSelectedItemId(R.id.nav_message);
            }
        }
    }

}
