package com.example.appdoctintuc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.appdoctintuc.databinding.ActivityMainBinding;
import com.example.appdoctintuc.fragment.BookMarkFragment;
import com.example.appdoctintuc.fragment.HomeFragment;
import com.example.appdoctintuc.fragment.SearchFragment;
import com.example.appdoctintuc.fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fragment fragment = new HomeFragment(this);
        loadFragment(fragment);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(item.getItemId() == R.id.action_home) {
                loadFragment(new HomeFragment(MainActivity.this));
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
            } else if (item.getItemId() == R.id.action_search) {
                loadFragment(new SearchFragment(MainActivity.this));
                bottomNavigationView.getMenu().getItem(1).setChecked(true);
            } else if (item.getItemId() == R.id.action_bookmark) {
                loadFragment(new BookMarkFragment(MainActivity.this));
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
            } else if (item.getItemId() == R.id.action_settings) {
                loadFragment(new SettingFragment(MainActivity.this));
                bottomNavigationView.getMenu().getItem(3).setChecked(true);
            }
            return false;
        }
    };


    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}