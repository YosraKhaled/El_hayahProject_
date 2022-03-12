package com.yosra.el_hayahproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.page_1:
                        homeFragment homeFragment=new homeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment , homeFragment).commit();
                        return true ;

                    case R.id.page_2:
                        profeilFragment profeilFragment=new profeilFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment , profeilFragment).commit();
                        return true ;
                }
                return false;
            }
        });
    }
}