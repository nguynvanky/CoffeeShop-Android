package com.example.coffeeshop.Activities;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.example.coffeeshop.Contains.UserIsLoggedIn;
import com.example.coffeeshop.Fragments.CartFragment;
import com.example.coffeeshop.Fragments.FavouriteFragment;
import com.example.coffeeshop.Fragments.HomeFragment;
import com.example.coffeeshop.Fragments.NotificationFragment;
import com.example.coffeeshop.R;
import com.example.coffeeshop.Services.CheckNetwork;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
     Fragment current = new HomeFragment();
     Integer current_page = R.id.page_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set Home selected
        Intent i = getIntent();
        if (i!= null)
        {
            if(i.getIntExtra("notification",-1) == 1)
            {
                current_page = R.id.page_4;
                current=  new NotificationFragment();
            }
        }
        loadFragment(current);

        bottomNavigationView.setSelectedItemId(current_page);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch(item.getItemId())
                {
                    case R.id.page_1:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.page_2:
                        fragment = new CartFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.page_3:
                        fragment = new FavouriteFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.page_4:
                        fragment = new NotificationFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        // transaction.addToBackStack(null);
        transaction.commit();
    }

    CheckNetwork checkNetwork = new CheckNetwork();

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter checkConnectedNetwork = new IntentFilter(CONNECTIVITY_ACTION);
        registerReceiver(checkNetwork, checkConnectedNetwork);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(checkNetwork);
    }
}